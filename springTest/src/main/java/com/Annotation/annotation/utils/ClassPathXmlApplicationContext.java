package com.Annotation.annotation.utils;

import com.Annotation.annotation.ioc.Component;
import com.Annotation.annotation.ioc.Controller;
import com.Annotation.annotation.ioc.Repository;
import com.Annotation.annotation.ioc.Service;
import com.spring.ApplicationContext;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 普通版本
 * @Author Mr.Li
 * @Date 2020/3/24 14:36
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
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

    public ClassPathXmlApplicationContext(String resourceName) {
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
    private void ioc(){
 if (CollectionUtils.isNotEmpty(classNames)){
     for (String className : classNames) {
         try {
             String value="";
             Class<?> aClass = Class.forName(className);
             //判断是否有ioc注解
             if (aClass.isAnnotationPresent(Service.class)||aClass.isAnnotationPresent(Controller.class)
             ||aClass.isAnnotationPresent(Component.class)||aClass.isAnnotationPresent(Repository.class)){
                 Service service = aClass.getAnnotation(Service.class);
                 if (service==null) {
                     Component component = aClass.getAnnotation(Component.class);
                     if (component==null){
                         Repository Repository = aClass.getAnnotation(Repository.class);
                         if (Repository==null){
                             Controller controller = aClass.getAnnotation(Controller.class);
                             if (controller!=null)
                                 value=controller.value();
                         }else {
                             value=Repository.value();
                         }
                     }else {
                         value=component.value();
                     }
                 }else {
                     value=service.value();
                 }
             }
             Object o = aClass.newInstance();
             map.put(value,o);
         } catch (Exception e) {
             throw new RuntimeException("创建"+className+"失败");
         }
     }
 }

    }

    private void di(){
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            // 获取集合中的对象
            Object value = stringObjectEntry.getValue();
            // 获取对象中的属性
            Field[] declaredFields = value.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Repository.class)) {
                    Repository annotation = declaredField.getAnnotation(Repository.class);
                    // 获取注解的value根据value提供的对象进行注入
                    String value1 = annotation.value();
                    Object bean = getBean(value1);
                    // 开启访问权限
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(value,bean);
                    } catch (IllegalAccessException e) {

                        throw new RuntimeException("注入失败");
                    }
                }
            }
        }
    }

    public Object getBean(String name) {
        Object o = map.get(name);
        if (o!=null)
            return o;
        throw new RuntimeException("容器中没有找到指定的bean"+name);
    }
}
