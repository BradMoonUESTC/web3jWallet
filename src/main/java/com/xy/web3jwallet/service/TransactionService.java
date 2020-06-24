package com.xy.web3jwallet.service;

import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthTransaction;

public interface TransactionService {
    /**
     * 发送转账事务
     *
     * @param _transaction_
     * @return transaction的hash值
     */
    String ethSendTransaction(Transaction _transaction_);

    /**
     * 通过hashcode获取transaction相关信息
     *
     * @param _hashcode_
     * @return
     */
    EthTransaction getTransactionByHash(String _hashcode_);

    /**
     * 每当项目启动，周期性的模拟不同账户间的转账
     *
     * @param
     * @return
     */
    void ScheduledSendTransaction();
}
