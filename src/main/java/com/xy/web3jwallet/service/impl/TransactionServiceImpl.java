package com.xy.web3jwallet.service.impl;

import com.xy.web3jwallet.service.AdminAccountService;
import com.xy.web3jwallet.service.BaseService;
import com.xy.web3jwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    BaseService baseService;

    @Autowired
    AdminAccountService adminAccountService;

    /**
     * 发送转账事务
     *
     * @param _transaction_
     * @return transaction的hash值
     */
    @Override
    public String ethSendTransaction(Transaction _transaction_) {
        Web3j web3j = baseService.initWeb3j();
        String transactionHash = "0x0000000000000000000000000000000000000000000000000000000000000000";
        Request<?, EthSendTransaction> request = web3j.ethSendTransaction(_transaction_);
        try {
            transactionHash = request.send().getTransactionHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionHash;
    }

    /**
     * 通过hashcode获取transaction相关信息
     *
     * @param _hashcode_
     * @return
     */
    @Override
    public EthTransaction getTransactionByHash(String _hashcode_) {
        Web3j web3j = baseService.initWeb3j();
        EthTransaction ethTransaction = new EthTransaction();
        Request<?, EthTransaction> request = web3j.ethGetTransactionByHash(_hashcode_);
        try {
            ethTransaction = request.sendAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ethTransaction;
    }

    /**
     * 每当项目启动，周期性的模拟不同账户间的转账
     * !!仅作测试使用
     * 临时为了方便编写，ganache里面10个账户每个给了10亿eth
     * 暂时不做余额判断了，后期要改
     * 2020-06-24
     *
     * @param
     * @return
     */

    @Override
    @Scheduled(cron = "0/10 * * * * MON-SAT")
    //暂定每10秒
    public void ScheduledSendTransaction() {
        Web3j web3j = baseService.initWeb3j();
        List<String> accounts = adminAccountService.getEthAccounts();
        Random r = new Random(1000);
        Integer value = r.nextInt(10);//随机金额
        Integer value_test2 = r.nextInt(20);
        String transactionHash = "0x0000000000000000000000000000000000000000000000000000000000000000";

        Transaction transaction = new Transaction(accounts.get(0),
                null,
                null,
                null,
                accounts.get(1),
                new BigInteger(String.valueOf(Convert.toWei(
                        value.toString(),
                        Convert.Unit.ETHER))),
                null);
        Request<?, EthSendTransaction> request_test1 = web3j.ethSendTransaction(transaction);

        transaction = new Transaction(accounts.get(0),
                null,
                null,
                null,
                accounts.get(2),
                new BigInteger(String.valueOf(Convert.toWei(
                        value_test2.toString(),
                        Convert.Unit.ETHER))),
                null);
        Request<?, EthSendTransaction> request_test2 = web3j.ethSendTransaction(transaction);

        try {
            request_test1.send();
            request_test2.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
