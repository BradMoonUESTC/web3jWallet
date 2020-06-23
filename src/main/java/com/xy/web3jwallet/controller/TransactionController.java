package com.xy.web3jwallet.controller;


import com.xy.web3jwallet.common.CommonResult;
import com.xy.web3jwallet.mbg.model.Transaction;
import com.xy.web3jwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.Optional;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    /**
     * 转账操作
     * @param transaction_model
     * @return
     */
    @PostMapping(value = "/sendtransaction")
    @ResponseBody
    public CommonResult<String> sendTransaction(@RequestBody Transaction transaction_model) {
        /**
         * 这里的transaction_model形参使用了自有类的实例，目的是为了对用户输入进行自定义的控制或者判断
         */
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
        if (!hash.equals("0x0000000000000000000000000000000000000000000000000000000000000000")) {
            commonResult = CommonResult.success(hash);
            //LOGGER.debug("");
        } else {
            commonResult = CommonResult.failed("操作失败");
            //LOGGER.debug("");
        }
        return commonResult;
    }

    /**
     * 根据hashcode获取transaction
     * @param hashcode
     * @return
     */
    @GetMapping(value = "/gettransaction/{hashcode}")
    public CommonResult<Optional<org.web3j.protocol.core.methods.response.Transaction>> getTransactionByHashCode(@PathVariable("hashcode") String hashcode){
        EthTransaction ethTransaction=transactionService.getTransactionByHash(hashcode);
        Optional<org.web3j.protocol.core.methods.response.Transaction> transaction=ethTransaction.getTransaction();
        return CommonResult.success(transaction);
    }


}
