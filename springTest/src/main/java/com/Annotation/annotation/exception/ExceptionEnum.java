package com.Annotation.annotation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum{
    INPUT_NOT_NULL(500,"输入为空"),
    RESULT_NULL(500,"返回结果为空"),
    NOT_HAVE_SECURITY(500,"没有权限")
    ;
     int status;
     String message;

}
