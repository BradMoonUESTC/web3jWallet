package com.xy.web3jwallet.common;

/**
 * 封装API的错误码
 * fork from http://www.macrozheng.com.
 * 2020-6
 * @author Nerbonic
 */
public interface IErrorCode {

    /**
     * 返回码
     * @return 返回码
     */
    long getCode();

    /**
     * 返回信息
     * @return 返回信息
     */
    String getMessage();
}