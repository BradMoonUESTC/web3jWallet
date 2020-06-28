package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.BaseService;
import com.xy.web3jwallet.service.WatchTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;

import java.io.IOException;

@Service
public class WatchTransactionServiceImpl implements WatchTransactionService {

    @Autowired
    BaseService baseService;

    /**
     * 一种监视方法：轮询最新块的交易或其他信息
     *
     * @return
     */
    @Override
    @Scheduled(cron = "0/5 * * * * MON-SAT")
    //每隔5秒轮询一次最新块
    public EthBlock.Block ScheduledCheckBlock() {
        Web3j web3j = baseService.initWeb3j();
        EthBlock ethBlock = null;
        try {
            ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).sendAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ethBlock.getBlock();
    }
}
