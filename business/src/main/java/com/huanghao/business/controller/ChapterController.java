package com.huanghao.business.controller;

import com.huanghao.server.domain.Chapter;
import com.huanghao.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HuangHao
 * @date 2020/8/7 0:24
 */
@RestController
public class ChapterController {


    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> chapter() {
        return chapterService.list();
    }
}
