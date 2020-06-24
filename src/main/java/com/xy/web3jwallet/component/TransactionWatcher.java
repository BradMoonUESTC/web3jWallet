package com.xy.web3jwallet.component;

import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

@Component
public class TransactionWatcher implements ApplicationRunner {


    @Autowired
    BaseService baseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Web3j web3j = baseService.initWeb3j();
        System.out.println("启动监听");

        /**
         * 坑1：4.0之后从observable改成了flowable，官方说是效率更高,
         * 但是flowable缓冲区是有大小限制的
         *
         * 坑2：没必要自己导rxjava依赖，去搞observable或者flowable，已经封装好了
         *
         * 坑3：不管是observable还是flowable都有时延，测试transaction10秒后才有输出
         * 猜测是是常见的缓冲区逻辑，满输出或者定时输出
         */

        /**
         * 4.0以上版本写法_转账监听
         */
        web3j.transactionFlowable().subscribe(transaction -> {
            System.out.println(transaction.getHash());

        });

        /**
         * 4.0以下的版本写法_转账监听
         */
//        web3j.transactionObservable().subscribe(transaction -> {
//            System.out.println(transaction);
//        });

    }
}
