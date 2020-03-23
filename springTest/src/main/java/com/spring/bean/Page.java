package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/21 13:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page {
    private Integer pageNumbers;//当前页数
    private Integer pageSize;//每页条数

    public Integer getPageNumbers() {
        return pageNumbers*this.pageSize;
    }
    public void setPageNumbers(Integer pageNumbers) {
        this.pageNumbers = pageNumbers*this.pageSize;
    }
}
