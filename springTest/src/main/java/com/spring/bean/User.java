package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String userPassword;
    private String salt;
}
