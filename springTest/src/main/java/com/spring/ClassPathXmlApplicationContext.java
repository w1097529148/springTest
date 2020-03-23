package com.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 手写SpringIOC（基于xml）
 * @Author Mr.Li
 * @Date 2020/3/17 13:43
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    public static final String CONTEXT_LOCATION="contextLocation";
    public static final String CONTEXT_SERVLETCONTEXT_NAME=ClassPathXmlApplicationContext.class.getName()+":spring";
    private Map<String,Object> map=new HashMap<>();//封装bean的容器
    @Override
    public Object getBean(String name) {
        Object o = map.get(name);
        if (o!=null)
            return o;
        throw new RuntimeException("容器中没有找到指定的bean"+name);
    }

    public ClassPathXmlApplicationContext(String resourcesName) {
        if(resourcesName==null||resourcesName.trim().equals(""))
            throw new RuntimeException("配置文件为空");


        Document document = getInputStream(resourcesName);
        //SelectNodes("item")
        //
        //从当前节点的儿子节点中选择名称为 item 的节点。
        //
        //SelectNodes("/item")
        //
        //从根节点的儿子节点中选择名称为 item 的节点。
        //
        //SelectNodes("//item")
        //
        //从任意位置的节点上选择名称为 item 的节点。要重点突出这个任意位置，它不受当前节点的影响，也就是说假如当前节点是在第 100 层（有点夸张），也可以选择第一层的名称为 item 的节点。
        List<Node> beans = document.selectNodes("//bean");
        ioc(beans);
        di(beans);
    }

    private Document getInputStream(String resourcesName){
        SAXReader saxReader = new SAXReader();
        InputStream resourceAsStream=null;
        //判断是否配置了classpath：
        if (resourcesName.startsWith("classpath:")){
            resourcesName=resourcesName.replace("classpath:","");
            resourceAsStream = ClassPathXmlApplicationContext.class.getClassLoader().getResourceAsStream(resourcesName);//返回读取指定资源的输入流。
        }else {
            try {
                resourceAsStream=new FileInputStream(resourcesName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

            if (resourceAsStream!=null) {
                try {
                    return saxReader.read(resourceAsStream);
                } catch (DocumentException e) {
                    e.printStackTrace();
                    throw new RuntimeException("文件不能为空");
                }
            }
                throw new RuntimeException("文件加载失败");
    }

    //dom4j解析xml结合反射创建对象
    private void ioc(List<Node> beans){
        if (beans!=null) {
            for (Node bean : beans) {
                //给定节点的类型字符串值
                String key = bean.valueOf("@id") != null ? bean.valueOf("@id") : bean.valueOf("@name");
                String className = bean.valueOf("@class");
                if ((key == null || key.trim().equals("")) || (className == null || className.trim().equals(""))) {
                    throw new RuntimeException("id或class不能为空");
                }
                try {
                    //反射创建对象
                    Object o = Class.forName(className).newInstance();
                    map.put(key, o);//放入容器
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("创建" + className + "失败");
                }
            }
        }else{
            throw new RuntimeException("请检查节点是否填写正确");
        }

    }

    //依赖注入
    private void di(List<Node> beans){
        if (beans!=null) {
            for (Node bean : beans) {
                String key = bean.valueOf("@id") != null ? bean.valueOf("@id") : bean.valueOf("@name");
                //根据key去容器中寻找对象
                Object o = map.get(key);
                Element element=(Element) bean;
                //返回给定属性的集合
                List<Element> property = element.elements("property");
                for (Element element1 : property) {
                    String name = element1.attributeValue("name");//返回属性的值，不存在就为空或者空串
                    String value = element1.attributeValue("value");
                    if (name == null || name.trim().equals(""))
                        throw new RuntimeException("name不能为空");
                    if (value!= null &&!(value.trim().equals(""))) {
                        try {
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, o.getClass());
                            Method writeMethod = propertyDescriptor.getWriteMethod();
                            if (propertyDescriptor.getPropertyType().getName().equals(Double.class.getName()))//double类型的数据
                                //普通属性赋值
                                writeMethod.invoke(o, Double.valueOf(value));
                            else
                                writeMethod.invoke(o, value);//String类型的数据
                        } catch (Exception e) {
                            throw new RuntimeException("普通属性赋值失败");
                        }
                    }
                else{
                        String ref = element1.attributeValue("ref");
                        if (ref!=null&&!ref.trim().equals("")){
                            Object o1 = map.get(ref);//ref存在于IOC容器中
                            try {
                                //创建一个属性描述器
                                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, o.getClass());
                                Method writeMethod = propertyDescriptor.getWriteMethod();//应用于写入属性值的方法set方法 又称内省。 如果不能写入属性，则可能返回null。
                                writeMethod.invoke(o,o1);//invoke方法用来在运行时动态地调用某个实例的方法
                            } catch (Exception e) {
                                throw new RuntimeException("对象属性赋值失败");
                            }
                        }

                    }
            }
        }
        }else{
            throw new RuntimeException("请检查节点是否填写正确");
        }
    }

    public static void main(String[] args) {
        System.out.println(ClassPathXmlApplicationContext.class.getName()+":spring");
    }
}
