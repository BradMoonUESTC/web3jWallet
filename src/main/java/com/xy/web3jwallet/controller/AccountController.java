package com.xy.web3jwallet.controller;

import com.xy.web3jwallet.common.CommonResult;
import com.xy.web3jwallet.service.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * 账户控制器，用来管理账户
 * 包括查询所有账户，创建新账户
 * @author Nerbonic
 */
@RestController
public class AccountController {


    @Autowired
    AdminAccountService adminAccountService;

    @GetMapping(value = "/listAll")
    public CommonResult<List<String>> getAccounts() {
        return CommonResult.success(adminAccountService.getEthAccounts());
    }

    @GetMapping(value = "/newAccount/{pwd}")
    public CommonResult<String> createAccount(@PathVariable("pwd") String pwd) {
        return CommonResult.success(adminAccountService.newAccount(pwd));

    }

    @GetMapping(value = "/gasprice")
    public CommonResult<BigInteger> getGasPrice() {
        return CommonResult.success(adminAccountService.getGasPrice());
    }

}
