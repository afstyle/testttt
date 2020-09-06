package com.huanghao.business.controller.admin;

import com.huanghao.server.dto.CourseDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.service.CourseService;
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
@RequestMapping("/admin/course")
public class CourseController {


    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";


    @Resource
    private CourseService courseService;

    /**
     * 列表查询
     */
    @PostMapping("/listCourse")
    public ResultVO listCourse(@RequestBody PageDTO pageDto) {
        courseService.listCourse(pageDto);
        return ResultVOUtil.success(pageDto);
    }

    /**
     * 保存
     */
    @PostMapping("/saveCourse")
    public ResultVO saveCourse(@RequestBody CourseDTO courseDto) {

        // 校验
        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        try {
            courseService.saveCourse(courseDto);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResultVOUtil.error(e.getMessage());
        }
        return ResultVOUtil.success(courseDto);
    }

    /**
     * 单个查询
     */
    @GetMapping("/getCourse/{id}")
    public ResultVO getCourse(@PathVariable String id) {
        // 校验
        ValidatorUtil.require(id, "id");

        CourseDTO courseDTO = courseService.getCourse(id);
        return ResultVOUtil.success(courseDTO);
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteCourse/{ids}")
    public ResultVO deleteCourse(@PathVariable String ids) {
        // 校验
        ValidatorUtil.require(ids, "ids");

        List<String> idList = CommonUtil.splitString2List(ids);
        if (CollectionUtils.isEmpty(idList)) {
            return ResultVOUtil.error(String.format("参数异常，ids = %s", ids));
        }
        idList.forEach(id -> {
            courseService.deleteCourse(id);
        });
        return ResultVOUtil.success();
    }

}
