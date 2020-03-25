package com.Annotation.annotation.test.bean;

import com.Annotation.annotation.di.Value;
import com.Annotation.annotation.ioc.Repository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/24 21:04
 */
@Repository("testBean")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class testBean {
    @Value("zs")
    private String name;
    @Value("18")
    private Integer age;
}
