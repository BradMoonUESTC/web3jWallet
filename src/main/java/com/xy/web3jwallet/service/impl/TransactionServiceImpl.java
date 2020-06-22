package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.BaseService;
import com.xy.web3jwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthTransaction;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    BaseService baseService;


    /**
     * 发送转账事务
     *
     * @param transaction
     * @return transaction的hash值
     */
    @Override
    public String ethSendTransaction(Transaction transaction) {
        Web3j web3j=baseService.initWeb3j();
        String transactionHash=null;
        try {
            transactionHash=web3j.ethSendTransaction(transaction).send().getTransactionHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionHash;
    }

    @Override
    public EthTransaction getTransactionByHash(String hashcode) {
        Web3j web3j=baseService.initWeb3j();
        EthTransaction ethTransaction=null;
        try {
            ethTransaction=web3j.ethGetTransactionByHash(hashcode).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ethTransaction;
    }
}
