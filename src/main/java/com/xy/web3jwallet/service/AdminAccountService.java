package com.xy.web3jwallet.service;

import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.math.BigInteger;
import java.util.List;

public interface AdminAccountService {

    /**
     * 获取所有账户列表
     * @return
     */
    List<String> getEthAccounts();

    /**
     * 创建新账户
     * @param password
     * @return
     */
    String newAccount(String password);

    /**
     * 获取gasprice
     * @return
     */
    BigInteger getGasPrice();

}
