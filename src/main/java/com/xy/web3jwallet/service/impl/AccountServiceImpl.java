package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.AccountService;
import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthAccounts;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    BaseService baseService;

    /**
     * 获取所有账户列表
     *
     * @return
     */
    @Override
    public List<String> getEthAccounts() {
        Web3j web3j = baseService.initWeb3j();

        EthAccounts result = new EthAccounts();
        try {
            result = web3j.ethAccounts()
                    .sendAsync()
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result.getAccounts();
    }

    /**
     * 创建新账户
     *
     * @param password
     * @return
     */
    @Override
    public String newAccount(String password) {
        Admin admin = baseService.initAdmin();
        Request<?, NewAccountIdentifier> request = admin.personalNewAccount(password);
        NewAccountIdentifier result = null;
        try {
            result = request.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.getAccountId();
    }
}
