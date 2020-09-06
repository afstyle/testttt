package com.huanghao.business.controller.admin;

import com.huanghao.server.dto.SectionDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.service.SectionService;
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

@RestController
@RequestMapping("/admin/section")
public class SectionController {


    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";


    @Resource
    private SectionService sectionService;

    /**
     * 列表查询
     */
    @PostMapping("/listSection")
    public ResultVO listSection(@RequestBody PageDTO pageDto) {
        sectionService.listSection(pageDto);
        return ResultVOUtil.success(pageDto);
    }

    /**
     * 保存
     */
    @PostMapping("/saveSection")
    public ResultVO saveSection(@RequestBody SectionDTO sectionDto) {

        // 校验
        ValidatorUtil.require(sectionDto.getTitle(), "标题");
        ValidatorUtil.length(sectionDto.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDto.getVideo(), "视频", 1, 200);

        try {
            sectionService.saveSection(sectionDto);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResultVOUtil.error(e.getMessage());
        }
        return ResultVOUtil.success(sectionDto);
    }

    /**
     * 单个查询
     */
    @GetMapping("/getSection/{id}")
    public ResultVO getSection(@PathVariable String id) {
        // 校验
        ValidatorUtil.require(id, "id");

        SectionDTO sectionDTO = sectionService.getSection(id);
        return ResultVOUtil.success(sectionDTO);
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteSection/{ids}")
    public ResultVO deleteSection(@PathVariable String ids) {
        // 校验
        ValidatorUtil.require(ids, "ids");

        List<String> idList = CommonUtil.splitString2List(ids);
        if (CollectionUtils.isEmpty(idList)) {
            return ResultVOUtil.error(String.format("参数异常，ids = %s", ids));
        }
        idList.forEach(id -> {
            sectionService.deleteSection(id);
        });
        return ResultVOUtil.success();
    }

}
