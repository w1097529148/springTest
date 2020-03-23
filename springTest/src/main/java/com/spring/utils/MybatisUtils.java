package com.spring.utils;

import com.spring.mapper.SecurityMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 11:48
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();
    static {

        try {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            InputStream inputStream=null;
            inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSessionFactory=builder.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载mybatis配置文件失败");
        }
    }
        /**
        *@Description: 类私有化
        *@Params
        *@Return
        *@Author Mr.Li
        *@Date 2020/3/21 11:58
        */
    private MybatisUtils(){}

    public static SqlSession getSqlSession(){
        // ThreadLocal类中get方法的作用是获取属于当前线程的变量的副本,操作全局变量时使用
        SqlSession sqlSession = threadLocal.get();
       if (sqlSession==null){
           sqlSession=sqlSessionFactory.openSession();
           threadLocal.set(sqlSession);
       }
       return sqlSession;
    }

    // 获取对应的Mapper，让映射器通过命名空间和方法名称找到对应的SQL，发送给数据库执行后返回结果。
    public static <T> T getMapper(Class<T> tClass){
        return getSqlSession().getMapper(tClass);
    }

    /**
    *@Description: 提交事务
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/18 12:59
    */
    public static void commitTransaction(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession!=null)
            sqlSession.commit();
    }

    /**
    *@Description: 回滚事务
    *@Params
    *@Return
    *@Author Mr.Li
    *@Date 2020/3/18 13:00
    */
    public static void rollbackTransaction(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession!=null)
            sqlSession.rollback();
    }

    public static void closeSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession!=null){
            try{
                sqlSession.close();
            }finally {
                threadLocal.remove();
            }
        }
    }
}
