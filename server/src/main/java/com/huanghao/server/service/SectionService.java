package com.huanghao.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huanghao.server.domain.Section;
import com.huanghao.server.domain.SectionExample;
import com.huanghao.server.dto.SectionDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.mapper.SectionMapper;
import com.huanghao.server.util.CopyUtil;
import com.huanghao.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
     * 列表查询
     */
    public void listSection(PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");

        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());

        List<SectionDTO> sectionDtoList = CopyUtil.copyList(sectionList, SectionDTO.class);
        pageDto.setList(sectionDtoList);
    }


    /**
     * 保存
     */
    public void saveSection(SectionDTO sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        Date nowDate = new Date();
        if (StringUtils.isEmpty(sectionDto.getId())) {
            section.setId(UuidUtil.getShortUuid());
            section.setCreatedAt(nowDate);
            section.setUpdatedAt(nowDate);
            sectionMapper.insert(section);
        } else {
            section.setUpdatedAt(nowDate);
            sectionMapper.updateByPrimaryKey(section);
        }
    }


    /**
     * 单个查询
     */
    public SectionDTO getSection(String id) {
        Section section = sectionMapper.selectByPrimaryKey(id);
        return CopyUtil.copy(section, SectionDTO.class);
    }

    /**
     * 删除
     */
    public void deleteSection(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}
