package com.xy.web3jwallet.controller;


import com.xy.web3jwallet.common.CommonResult;
import com.xy.web3jwallet.mbg.model.Transaction;
import com.xy.web3jwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.Optional;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    /**
     * 转账操作
     *
     * @param transactionModel
     * @return
     */
    @PostMapping(value = "/sendtransaction")
    @ResponseBody
    public CommonResult<String> sendTransaction(@RequestBody Transaction transactionModel) {
        /**
         * 这里的transaction_model形参暂时使用了自有类的实例，考虑后面可以在切面搞点东西
         */
        CommonResult commonResult;
        org.web3j.protocol.core.methods.request.Transaction transaction =
                new org.web3j.protocol.core.methods.request.Transaction(
                        transactionModel.getFrom(),
                        transactionModel.getNonce(),
                        transactionModel.getGasPrice(),
                        transactionModel.getGas(),
                        transactionModel.getTo(),
                        transactionModel.getValue(),
                        transactionModel.getData());

        EthSendTransaction ethSendTransaction = transactionService.ethSendTransaction(transaction);
            commonResult = CommonResult.success(ethSendTransaction.hashCode());
        return commonResult;
    }

    /**
     * 根据hashcode获取transaction
     *
     * @param hashcode
     * @return
     */
    @GetMapping(value = "/gettransaction/{hashcode}")
    public CommonResult<Optional<org.web3j.protocol.core.methods.response.Transaction>> getTransactionByHashCode(@PathVariable("hashcode") String hashcode) {
        EthTransaction ethTransaction = transactionService.getTransactionByHash(hashcode);
        Optional<org.web3j.protocol.core.methods.response.Transaction> transaction = ethTransaction.getTransaction();
        return CommonResult.success(transaction);
    }


}
