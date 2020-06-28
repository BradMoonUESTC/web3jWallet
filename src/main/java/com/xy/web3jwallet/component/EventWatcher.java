package com.xy.web3jwallet.component;

import com.xy.web3jwallet.config.EthFilterConfig;
import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.Contract;

import java.util.Arrays;

/**
 * 事件监控
 */
@Component
public class EventWatcher implements ApplicationRunner {

    @Autowired
    BaseService baseService;

    @Autowired
    EthFilterConfig ethFilterConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Web3j web3j = baseService.initWeb3j();
        EthFilter ethFilter = ethFilterConfig.ethFilter();

        Event event = new Event("senddata",
                Arrays.asList(
                        new TypeReference<Address>() {
                        },
                        new TypeReference<Utf8String>() {
                        }
                ));

        ethFilter.addSingleTopic(EventEncoder.encode(event));
        web3j.ethLogFlowable(ethFilter).subscribe(log -> {
            System.out.println("监测到事件");

            //从log中抽取arg中的event返回值
            //关键方法：Contract.staticExtractEventParameters(event, log)
            EventValues eventValues = Contract.staticExtractEventParameters(event, log);
            System.out.println(eventValues.getNonIndexedValues());
            System.out.println(eventValues.getNonIndexedValues().get(0));
            System.out.println(eventValues.getNonIndexedValues().get(1));

        });
        //单纯的以log订阅事件
        /**
         web3j.ethLogFlowable(ethFilter).subscribe(event->{
         System.out.println(event.getAddress());
         });
         */
    }
}
