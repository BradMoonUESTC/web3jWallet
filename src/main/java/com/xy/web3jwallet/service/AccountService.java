package com.xy.web3jwallet.service;

import java.util.List;

public interface AccountService {

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

}
