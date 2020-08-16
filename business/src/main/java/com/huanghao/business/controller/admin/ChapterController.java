package com.huanghao.business.controller.admin;

import com.huanghao.server.dto.ChapterDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author HuangHao
 * @date 2020/8/7 0:24
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {


    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    
    
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public PageDTO list(@RequestBody PageDTO pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }

    @RequestMapping("/save")
    public ChapterDTO save(@RequestBody ChapterDTO chapterDto) {
        LOG.info("chapterDto: {}", chapterDto);
        chapterService.save(chapterDto);
        return chapterDto;
    }

}
