package com.xy.web3jwallet.common;

/**
 * 封装API的错误码
 * fork from http://www.macrozheng.com.
 * 2020-6
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}