package com.xy.web3jwallet.service;

import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthTransaction;

public interface TransactionService {
    /**
     * 发送转账事务
     * @param transaction
     * @return transaction的hash值
     */
    String ethSendTransaction(Transaction transaction);

    EthTransaction getTransactionByHash(String hashcode);
}