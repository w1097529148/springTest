package com.Annotation.annotation.exception;

import lombok.Getter;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/25 12:34
 */
@Getter
public class ExceptionBean {
    private int status;//状态码
    private String message;//返回信息
    private Long time;//时间戳

    public ExceptionBean(ExceptionEnum exceptionEnum) {
        this.status = exceptionEnum.status;
        this.message = exceptionEnum.message;
        this.time = System.currentTimeMillis();
    }
}
