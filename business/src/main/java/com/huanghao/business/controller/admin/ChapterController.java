package com.huanghao.business.controller.admin;

import com.huanghao.server.dto.ChapterDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.service.ChapterService;
import com.huanghao.server.util.CommonUtil;
import com.huanghao.server.util.ResultVOUtil;
import com.huanghao.server.util.ValidatorUtil;
import com.huanghao.server.vo.commons.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
    public static final String BUSINESS_NAME = "大章";
    
    
    @Resource
    private ChapterService chapterService;

    /**
     * 列表查询
     */
    @PostMapping("/listChapter")
    public ResultVO listChapter(@RequestBody PageDTO pageDto) {
        chapterService.listChapter(pageDto);
        return ResultVOUtil.success(pageDto);
    }

    /**
     * 保存
     */
    @PostMapping("/saveChapter")
    public ResultVO saveChapter(@RequestBody ChapterDTO chapterDto) {

        // 校验
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        try {
            chapterService.saveChapter(chapterDto);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResultVOUtil.error(e.getMessage());
        }
        return ResultVOUtil.success(chapterDto);
    }

    /**
     * 单个查询
     */
    @GetMapping("/getChapter/{id}")
    public ResultVO getChapter(@PathVariable String id) {
        // 校验
        ValidatorUtil.require(id, "id");

        ChapterDTO chapterDTO = chapterService.getChapter(id);
        return ResultVOUtil.success(chapterDTO);
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteChapter/{ids}")
    public ResultVO deleteChapter(@PathVariable String ids) {
        // 校验
        ValidatorUtil.require(ids, "ids");

        List<String> idList = CommonUtil.splitString2List(ids);
        if (CollectionUtils.isEmpty(idList)) {
            return ResultVOUtil.error(String.format("参数异常，ids = %s", ids));
        }
        idList.forEach(id -> {
            chapterService.deleteChapter(id);
        });
        return ResultVOUtil.success();
    }

}
