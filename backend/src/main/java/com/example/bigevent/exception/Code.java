package com.example.bigevent.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Code {

    //错误信息
    LOGIN_ERROR(Code.ERROR,"账号密码错了"),
    BAD_REQUEST(Code.ERROR,"请求错误"),
    UNAUTHORIZED(401,"未登录"),
    TOKEN_EXPIRED(403,"过期请重新登陆"),
    HAD_USER(Code.ERROR,"用户已存在"),
    FORBIDDEN(403,"无权限");

    public static final int ERROR = 400;
    private final int code;
    private final String message;
}
