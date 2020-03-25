package com.Annotation.annotation.utils;

import com.Annotation.annotation.UserThreadLocal;
import com.Annotation.annotation.aop.Aspect;
import com.Annotation.annotation.di.Autowired;
import com.Annotation.annotation.di.Resource;
import com.Annotation.annotation.di.Value;
import com.Annotation.annotation.exception.ExceptionBean;
import com.Annotation.annotation.exception.ExceptionEnum;
import com.Annotation.annotation.exception.MyException;
import com.Annotation.annotation.ioc.Component;
import com.Annotation.annotation.ioc.Controller;
import com.Annotation.annotation.ioc.Repository;
import com.Annotation.annotation.ioc.Service;
import com.Annotation.annotation.security.security;
import com.spring.ApplicationContext;
import com.spring.bean.Privilege;
import com.spring.bean.User;
import com.spring.serviceImpl.SecurityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Description 全新版本
 * @Author Mr.Li
 * @Date 2020/3/24 14:36
 */
public class ClassPathXmlApplicationContext1 implements ApplicationContext {
    public static final String CONTEXT_LOCATION="contextLocation";
    public static final String CONTEXT_SERVLETCONTEXT_NAME=ClassPathXmlApplicationContext1.class.getName()+":spring";
    private List<String> classNames=new ArrayList<>();
    private Map<String,Object> map=new HashMap<>();
    private void scanPackage(String packageName){
        URL resource = this.getClass().getClassLoader().getResource(replaceTo(packageName));
        System.out.println("resource = " + resource);
        if (resource!=null){
            //获取文件夹路径
            String file = resource.getFile();
            File file1 = new File(file);
            // 获取指定文件夹下的文件和文件夹
            String[] list = file1.list();
            for (String s : list) {
                File file2 = new File(file + File.separator + s);
                // 如果是文件夹递归
                if (file2.isDirectory()){
                    scanPackage(packageName+"."+file2.getName());
                } else {
                    if (file2.getName().endsWith(".class")){
                        String s1 = packageName + "." + file2.getName().replace(".class", "");
                        classNames.add(s1);
                    }
                }
            }

        }

    }

    private String replaceTo(String packageName) {
        return packageName.replaceAll("\\.","/");
    }

    public ClassPathXmlApplicationContext1(String resourceName) {
        if (StringUtils.isBlank(resourceName))
            throw new RuntimeException("配置文件不能为空");
        InputStream in=null;
        //判断是否走相对路径
        if (resourceName.contains("classpath:")){
            in=this.getClass().getClassLoader().getResourceAsStream(resourceName.replace("classpath:",""));
        }else{
            try {
                in =new FileInputStream(resourceName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("找不到指定的配置文件");
            }
        }
        SAXReader saxReader = new SAXReader();
        try {
            Document document=saxReader.read(in);
            Node node = document.selectSingleNode("//component-scan");
            if (node==null)
                throw new RuntimeException("没有配置扫描包");
            String basePackage = node.valueOf("@base-package");
            String[] split = basePackage.split(",");
            for (String s : split) {
                scanPackage(s);
            }
            ioc();
            di();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //ioc将对象交给Spring容器管理
    public void ioc(){
     for (String className : classNames) {
         Object o=null;
         try {
             String value="";
             final Class<?> aClass = Class.forName(className);
             //判断是否有ioc注解
             if (aClass.isAnnotationPresent(Service.class)||aClass.isAnnotationPresent(Controller.class)
             ||aClass.isAnnotationPresent(Component.class)||aClass.isAnnotationPresent(Repository.class)){
                 Service service = aClass.getAnnotation(Service.class);
                 if (service==null) {
                     Component component = aClass.getAnnotation(Component.class);
                     if (component==null){
                         Repository respository = aClass.getAnnotation(Repository.class);
                         if (respository==null){
                             Controller controller = aClass.getAnnotation(Controller.class);
                             if (controller!=null)
                                 value=controller.value();
                         }else {
                             value=respository.value();
                         }
                     }else {
                         value=component.value();
                     }
                 }else {
                     value=service.value();
                 }
                 o = aClass.newInstance();
             }
             if(aClass.isAnnotationPresent(Aspect.class)) {
                 Class<?> aClass1 = Class.forName(className);
                final Object o1 = aClass1.newInstance();

                 o= Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new InvocationHandler() {
                     @Override
                     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                         System.out.println("目标方法执行"+method.getName());
                         // 获得真实的方法
                         Method method1 = aClass.getMethod(method.getName(), method.getParameterTypes());
                         // 判断是否有权限注解，权限鉴定
                         if (method1.isAnnotationPresent(security.class)){
                             security security = method1.getAnnotation(security.class);
                             // 获取value
                             String value1 = security.value();
                             User currentUser = UserThreadLocal.getCurrentUser();
                             if ("admin".equals(currentUser.getUserName())){
                                 SecurityService securityService = new SecurityService();
                                 List<Privilege> privileges = securityService.querySecurityByRoleId(currentUser.getId());
                                 Boolean isOk=false;
                                 for (Privilege privilege : privileges) {
                                     if (privilege.getName().equals(value1))
                                         isOk=true;
                                     break;
                                 }
                                 if (isOk)
                                     return method.invoke(o1,args);
                                 throw new MyException(ExceptionEnum.NOT_HAVE_SECURITY);
                             }
                         }
                         return method.invoke(o1,args);
                     }
                 });
             }
             if(o!=null){
                 //按照类名存储
                 if (StringUtils.isNotBlank(value)){
                     map.put(value,o);
                 }
                 //按照全限定类名进行存储
                 map.put(aClass.getName(),o);
                 //按照接口存储
                 Class<?>[] interfaces = aClass.getInterfaces();
                 if (interfaces!=null)
                     for (Class<?> anInterface : interfaces) {
                         map.put(anInterface.getName(),o);
                     }
             }
         } catch (Exception e) {
             throw new RuntimeException("创建"+className+"失败");
         }
     }

    }

    public void di(){
        if (map!=null)
        try {
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                // 获取集合中的对象
                Object obj = stringObjectEntry.getValue();
                if (obj != null) {
                    // 获取对象中的属性
                    Field[] declaredFields = obj.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        String name = "";
                        // 按照类型注入
                        if (declaredField.isAnnotationPresent(Autowired.class)) {
                            name = declaredField.getType().getName();
                            System.out.println("Autowiredname = " + name);
                            //按照指定类名注入
                        } else if (declaredField.isAnnotationPresent(Resource.class)) {
                            Resource annotation = declaredField.getAnnotation(Resource.class);
                            // 获取注解的value根据value提供的对象进行注入
                            name = annotation.name();
                        } else if (declaredField.isAnnotationPresent(Value.class)) {
                            Value value1 = declaredField.getAnnotation(Value.class);
                            String value2 = value1.value();
                            declaredField.setAccessible(true);
                            //如果是Integer类型
                            if ("java.lang.Integer".equals(declaredField.getType().getName()))
                                declaredField.set(obj, Integer.valueOf(value2));
                            else
                                //如果是String类型
                                declaredField.set(obj, value2);
                        }
                        if (!"".equals(name)) {
                            Object bean = getBean(name);
                            // 开启访问权限
                            declaredField.setAccessible(true);
                            declaredField.set(obj, bean);
                        }
                    }
                }
            }
        }catch (IllegalAccessException e) {
            throw new RuntimeException("注入失败");
        }
    }

    public Object getBean(String name) {
        Object o = map.get(name);
        if (o!=null){
            System.out.println("o = " + o);
            return o;
        }

        throw new RuntimeException("容器中没有找到指定的bean"+name);
    }
}
