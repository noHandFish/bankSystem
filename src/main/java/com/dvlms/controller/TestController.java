package com.dvlms.controller;

import cn.hutool.core.map.MapUtil;
import com.dvlms.common.lang.Result;
import com.dvlms.entity.ClientContract;
import com.dvlms.service.BussinessInfoService;
import com.dvlms.service.ClientContractService;
import com.dvlms.service.UserRoleService;
import com.dvlms.serviceImpl.ClientContractServiceImpl;
import com.dvlms.serviceImpl.ClientInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController<T> {
    @Autowired
    BussinessInfoService bussinessInfoService;
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("clientContractServiceImpl")
    private ClientContractService clientContractService;

    @RequestMapping("/test")
    public Object test() {
//        return new Result<>(bussinessInfoService.list());
//        return testAdd();
//        System.out.println("你好");

        return new Result<>(userRoleService.list());
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/test/passEncode")
    public Object passEncode() {
        // 密码加密
        String pass = bCryptPasswordEncoder.encode("password");

        // 密码验证
        boolean matches = bCryptPasswordEncoder.matches("password", pass);

        return new Result<>(MapUtil.builder()
                .put("pass", pass)
                .put("marches", matches)
                .build()
        );
    }

    public boolean testAdd() {
        String conId = "DYC202201011";
        ClientContract clientContract = new ClientContract("钟某11", "440301xxxxxxxx1111", conId);
        return clientContractService.save(clientContract);
    }
}
