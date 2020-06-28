package com.xy.web3jwallet.service;


import org.web3j.protocol.core.methods.response.EthBlock;

public interface WatchTransactionService {

    /**
     * 一种监视方法：轮询最新块的交易或其他信息
     *
     * @return 区块信息
     */
    EthBlock.Block ScheduledCheckBlock();

}


