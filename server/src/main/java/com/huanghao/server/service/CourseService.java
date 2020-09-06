package com.huanghao.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huanghao.server.domain.Course;
import com.huanghao.server.domain.CourseExample;
import com.huanghao.server.dto.CourseDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.mapper.CourseMapper;
import com.huanghao.server.util.CopyUtil;
import com.huanghao.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 列表查询
     */
    public void listCourse(PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");

        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseDTO> courseDtoList = CopyUtil.copyList(courseList, CourseDTO.class);
        pageDto.setList(courseDtoList);
    }


    /**
     * 保存
     */
    public void saveCourse(CourseDTO courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        Date nowDate = new Date();
        if (StringUtils.isEmpty(courseDto.getId())) {
            course.setId(UuidUtil.getShortUuid());
            course.setCreatedAt(nowDate);
            course.setUpdatedAt(nowDate);
            courseMapper.insert(course);
        } else {
            course.setUpdatedAt(nowDate);
            courseMapper.updateByPrimaryKey(course);
        }
    }


    /**
     * 单个查询
     */
    public CourseDTO getCourse(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return CopyUtil.copy(course, CourseDTO.class);
    }

    /**
     * 删除
     */
    public void deleteCourse(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }
}
