package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.AdminAccountService;
import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGasPrice;

import java.math.BigInteger;
import java.util.List;

@Service
public class AdminAccountServiceImpl implements AdminAccountService {

    @Autowired
    BaseService baseService;

    /**
     * 获取gasprice
     *
     * @return gasprice
     */
    @Override
    public BigInteger getGasPrice() {
        Web3j web3j = baseService.initWeb3j();

        EthGasPrice ethGasPrice = new EthGasPrice();
        Request<?, EthGasPrice> request = web3j.ethGasPrice();
        try {
            ethGasPrice = request.sendAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ethGasPrice.getGasPrice();
    }

    /**
     * 获取所有账户列表
     *
     * @return 账户列表
     */
    @Override
    public List<String> getEthAccounts() {
        Web3j web3j = baseService.initWeb3j();

        EthAccounts result = new EthAccounts();
        Request<?, EthAccounts> request = web3j.ethAccounts();

        try {
            result = request.sendAsync().get();//异步请求
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.getAccounts();
    }

    /**
     * 创建新账户
     *
     * @param _password_ 账户密码
     * @return 账户地址
     */
    @Override
    public String newAccount(String _password_) {
        Admin admin = baseService.initAdmin();

        Request<?, NewAccountIdentifier> request = admin.personalNewAccount(_password_);
        NewAccountIdentifier result = new NewAccountIdentifier();
        try {
            result = request.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getAccountId();
    }
}
