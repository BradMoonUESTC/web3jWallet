package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.BaseService;
import com.xy.web3jwallet.service.WatchTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

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
    //每隔5秒轮询一次
    public EthBlock.Block ScheduledCheckBlock() {
        Web3j web3j=baseService.initWeb3j();
        EthBlock ethBlock = null;
        try {
            BigInteger blocknumber=web3j.ethBlockNumber().send().getBlockNumber();
            //获取最新的blocknumber
            System.out.println(blocknumber);
            DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(blocknumber);

            ethBlock=web3j.ethGetBlockByNumber(defaultBlockParameter,false).sendAsync().get();
            System.out.println(ethBlock.getBlock().getHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ethBlock.getBlock();
    }
}
