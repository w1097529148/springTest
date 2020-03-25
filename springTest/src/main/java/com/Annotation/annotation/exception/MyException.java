package com.Annotation.annotation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 自定义异常
 * @Author Mr.Li
 * @Date 2020/3/25 11:08
 */
@NoArgsConstructor
public class MyException extends RuntimeException {
    private ExceptionBean exceptionBean;

    public MyException(ExceptionEnum exceptionEnum) {
        this.exceptionBean = new ExceptionBean(exceptionEnum);
    }
}
