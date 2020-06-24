package com.xy.web3jwallet.config;

import com.xy.web3jwallet.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.tx.TransactionManager;

import java.io.IOException;
import java.math.BigInteger;

@Configuration
public class EthFilterConfig {

    @Autowired
    BaseService baseService;



    @Bean
    @Scope("prototype")
    public EthFilter ethFilter() throws IOException {
        Web3j web3j=baseService.initWeb3j();
        return new EthFilter(DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST, "0x39e03b156b4ea519464e5eeb9bebec78b1f0a54b");
    }
}
