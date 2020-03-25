package com.Annotation.annotation.Service;

import com.Annotation.annotation.di.Autowired;
import com.Annotation.annotation.test.bean.testBean;
import com.Annotation.annotation.di.Resource;
import com.Annotation.annotation.ioc.Service;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/24 21:03
 */
@Service("testService")
public class testService {
    @Autowired
    private testBean testBean;
    public void sayhello(){
        System.out.println("hello"+testBean);
    }
}
