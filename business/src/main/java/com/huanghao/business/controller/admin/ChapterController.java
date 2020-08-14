package com.huanghao.business.controller.admin;

import com.huanghao.server.domain.Chapter;
import com.huanghao.server.dto.ChapterDto;
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
@RequestMapping("/admin/chapter")
public class ChapterController {


    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public List<ChapterDto> lsit() {
        return chapterService.list();
    }
}
