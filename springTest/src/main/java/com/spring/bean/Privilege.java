package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 22:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {
    private Integer id;
    private String name;
    private String uri;
    private String description;
}
