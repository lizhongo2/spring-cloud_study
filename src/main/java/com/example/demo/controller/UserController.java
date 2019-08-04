package com.example.demo.controller;

import com.example.demo.service.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("hello")
    public String hello(String userName) {
        CacheManager cacheManager = BeanUtils.applicationContext.getBean(CacheManager.class);
        logger.info(cacheManager.getClass().getName());
        return "hello:" + userName;
    }

}
