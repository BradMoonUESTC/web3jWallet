package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

@Service
public class BaseServiceImpl implements BaseService {

    //@Value("${baseservice.url}")
    private static String URL="http://127.0.0.1:8545";


    /**
     * 初始化web3j普通api调用
     *
     * @return web3j
     */
    @Override
    public Web3j initWeb3j() {
        return Web3j.build(getService());
    }

    /**
     * 初始化personal级别的操作对象
     * @return Geth
     */
    @Override
    public Geth initGeth() {
        return Geth.build(getService());
    }

    /**
     * 初始化admin级别操作的对象
     * @return Admin
     */
    @Override
    public Admin initAdmin() {
        return Admin.build(getService());
    }

    /**
     * 通过http连接到geth节点
     * @return
     */
    @Override
    public HttpService getService() {
        return new HttpService(URL);
    }
}
