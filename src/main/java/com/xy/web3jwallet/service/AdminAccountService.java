package com.xy.web3jwallet.service;

import java.math.BigInteger;
import java.util.List;

public interface AdminAccountService {

    /**
     * 获取所有账户列表
     *
     * @return
     */
    List<String> getEthAccounts();

    /**
     * 创建新账户
     *
     * @param password
     * @return
     */
    String newAccount(String password);

    /**
     * 获取gasprice
     *
     * @return
     */
    BigInteger getGasPrice();

}
