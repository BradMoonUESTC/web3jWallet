package com.xy.web3jwallet.demo;

import com.xy.web3jwallet.service.BaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    BaseService baseService;
    @Test
    void WatchTest(){
        Web3j web3j=baseService.initWeb3j();

        /**
         * Create an Flowable to emit block hashes.
         * Returns:
         * a instance that emits all new block hashes
         * as new blocks are created on the blockchainFlowable
         *
         *
         */
        //Observable<Transaction> transactionObservable = web3j.transactionObservable();

    }

}
