package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.BaseService;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

/**
 * @author Nerbonic
 */
@Service
public class BaseServiceImpl implements BaseService {

    private static final String URL = "http://127.0.0.1:8545";


    /**
     * 初始化web3j
     *
     * @return web3j
     */
    @Override
    public Web3j initWeb3j() {
        return Web3j.build(getService());
    }

    /**
     * 初始化geth
     *
     * @return Geth
     */
    @Override
    public Geth initGeth() {
        return Geth.build(getService());
    }

    /**
     * 初始化admin
     *
     * @return Admin
     */
    @Override
    public Admin initAdmin() {
        return Admin.build(getService());
    }

    /**
     * 通过http连接到geth节点
     *
     * @return httpservice
     */
    @Override
    public HttpService getService() {
        return new HttpService(URL);
    }
}
