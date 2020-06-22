package com.xy.web3jwallet.controller;


import com.xy.web3jwallet.common.CommonResult;
import com.xy.web3jwallet.mbg.model.Transaction;
import com.xy.web3jwallet.service.AdminAccountService;
import com.xy.web3jwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/sendtransaction")
    @ResponseBody
    public CommonResult<String> sendTransaction(@RequestBody Transaction transaction_model) {
        CommonResult commonResult;
        org.web3j.protocol.core.methods.request.Transaction transaction=
                new org.web3j.protocol.core.methods.request.Transaction(
                        transaction_model.getFrom(),
                        transaction_model.getNonce(),
                        transaction_model.getGasPrice(),
                        transaction_model.getGas(),
                        transaction_model.getTo(),
                        transaction_model.getValue(),
                        transaction_model.getData());

        String hash = transactionService.ethSendTransaction(transaction);
        if (!hash.equals("0x000000000000000000000000000000000000000000000000000000000000000")) {
            commonResult = CommonResult.success(hash);
            //LOGGER.debug("createStudent success:{}", transaction);
        } else {
            commonResult = CommonResult.failed("操作失败");
            //LOGGER.debug("createStudent failed:{}", transaction);
        }
        return commonResult;
    }


}
