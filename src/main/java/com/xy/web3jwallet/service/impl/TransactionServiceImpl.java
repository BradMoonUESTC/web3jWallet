package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.BaseService;
import com.xy.web3jwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
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
        String transactionHash="0x0000000000000000000000000000000000000000000000000000000000000000";
        Request<?, EthSendTransaction> request=web3j.ethSendTransaction(transaction);
        try {
            transactionHash=request.send().getTransactionHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionHash;
    }

    @Override
    public EthTransaction getTransactionByHash(String hashcode) {
        Web3j web3j=baseService.initWeb3j();
        EthTransaction ethTransaction=new EthTransaction();
        Request<?, EthTransaction> request=web3j.ethGetTransactionByHash(hashcode);
        try {
            ethTransaction=request.sendAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ethTransaction;
    }
}
