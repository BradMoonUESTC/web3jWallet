package com.xy.web3jwallet.config;

import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.filters.Callback;
import org.web3j.protocol.core.filters.Filter;
import org.web3j.protocol.core.filters.PendingTransactionFilter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Transaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

@Configuration
public class WatcherConfig {
    @Autowired
    BaseService baseService;

    /**
     * 多例监听，scope
     * @return
     */
    @Bean
    @Scope("prototype")
    public Web3j web3j(){
        return baseService.initWeb3j();
    }

    /**
     * 单纯的挂起交易的监视器
     * @param web3j
     * @return
     * @throws IOException
     */
    @Bean
    @Scope("prototype")
    @Autowired
    public EthFilter ethFilter(Web3j web3j) throws IOException {
        EthFilter ethFilter=new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST,"0x59eb99d9f6fa5d3d2229effcb811ac574708248d");
        EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
        //web3j.ethBlockHashObservable()
        return ethFilter(web3j);

    }
}
