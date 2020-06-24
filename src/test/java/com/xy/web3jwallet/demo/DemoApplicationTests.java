package com.xy.web3jwallet.demo;

import com.xy.web3jwallet.service.BaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    BaseService baseService;

    @Test
    void contextLoads() {
        WatchTest();
    }

    @Test
    void WatchEntrance() {
        WatchTest();
    }

    @Test
    void WatchTest() {
        Web3j web3j = baseService.initWeb3j();

        /**
         *  Create an instance
         *  that emits newly created blocks
         *  on the blockchain.Flowable
         *
         * 当创建出一个新区块时，创建这个区块的实例，放在Flowable中
         * 后面的subscribe为进行广播
         * true表示返回的是这个区块上的所有transaction的信息
         * false表示只返回区块的hash
         */


    }

}
