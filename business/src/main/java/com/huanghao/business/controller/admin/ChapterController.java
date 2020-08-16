package com.huanghao.business.controller.admin;

import com.huanghao.server.domain.Chapter;
import com.huanghao.server.dto.ChapterDto;
import com.huanghao.server.dto.PageDto;
import com.huanghao.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public PageDto list(@RequestBody PageDto pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }

    @RequestMapping("/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDto: {}", chapterDto);
        chapterService.save(chapterDto);
        return chapterDto;
    }

}
