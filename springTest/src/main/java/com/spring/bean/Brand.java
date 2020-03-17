package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/17 13:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private String name;
    private Double price;
    private String address;
    private Car car;
}

