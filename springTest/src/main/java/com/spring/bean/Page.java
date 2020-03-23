package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/21 13:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<E> {
    private int total;//信息总数
    private int page;//当前页
    private int size;//每页信息条数
    private List<E> rows;//每一页的信息存放的列表

}
