package com.dvlms.controller;

import com.dvlms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class BaseContoller {
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    RedisUtil redisUtil;
}
