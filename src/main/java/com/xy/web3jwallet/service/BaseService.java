package com.xy.web3jwallet.service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;


public interface BaseService {

    /**
     * 初始化web3j普通api调用
     *
     * @return web3j
     */
    Web3j initWeb3j();

    /**
     * 初始化personal级别的操作对象
     * @return Geth
     */
    Geth initGeth();

    /**
     * 初始化admin级别操作的对象
     * @return Admin
     */
    Admin initAdmin();

    /**
     * 通过http连接到geth节点
     * @return
     */
    HttpService getService();
}
