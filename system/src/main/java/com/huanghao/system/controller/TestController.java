package com.huanghao.system.controller;

import com.huanghao.system.domain.Test;
import com.huanghao.system.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuangHao
 * @date 2020/8/7 0:24
 */
@RestController
public class TestController {


    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public List<Test> test() {
        return testService.list();
    }
}
