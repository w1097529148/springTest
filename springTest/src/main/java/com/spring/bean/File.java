package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private Integer id;
    private String fileName;
    private String filePath;
    private String suffix;
    private Date created;
    private Date update;
    private User user;
}
