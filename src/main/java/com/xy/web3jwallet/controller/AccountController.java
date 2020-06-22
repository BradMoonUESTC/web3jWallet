package com.xy.web3jwallet.controller;

import com.xy.web3jwallet.common.CommonResult;
import com.xy.web3jwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {


    @Autowired
    AccountService accountService;

    @GetMapping(value = "/listAll")
    public CommonResult<List<String>> getAccounts(){
        return CommonResult.success(accountService.getEthAccounts());
    }

    @GetMapping(value="/newAccount/{pwd}")
    public CommonResult<String> createAccount(@PathVariable("pwd") String pwd){
        return CommonResult.success(accountService.newAccount(pwd));


    }
}
