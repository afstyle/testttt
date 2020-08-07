package com.huanghao.system.service;

import com.huanghao.system.domain.Test;
import com.huanghao.system.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuangHao
 * @date 2020/8/7 17:17
 */
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
