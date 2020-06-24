package com.xy.web3jwallet.service;

import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthTransaction;

public interface TransactionService {
    /**
     * 发送转账事务
     *
     * @param transaction
     * @return transaction的hash值
     */
    String ethSendTransaction(Transaction transaction);

    /**
     * 通过hashcode获取transaction相关信息
     *
     * @param hashcode
     * @return
     */
    EthTransaction getTransactionByHash(String hashcode);

    /**
     * 每当项目启动，周期性的模拟不同账户间的转账
     *
     * @param
     * @return
     */
    void ScheduledSendTransaction();
}
