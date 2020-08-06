package com.huanghao.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuangHao
 * @date 2020/8/7 0:24
 */
@RestController
public class TestController {


    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
