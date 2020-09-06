package com.huanghao.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huanghao.server.domain.${Domain};
import com.huanghao.server.domain.${Domain}Example;
import com.huanghao.server.dto.${Domain}DTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.mapper.${Domain}Mapper;
import com.huanghao.server.util.CopyUtil;
import com.huanghao.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
<#list typeSet as type>
    <#if type == 'Date'>
import java.util.Date;
    </#if>
</#list>

@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * 列表查询
     */
    public void list${Domain}(PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        <#list fieldList as field>
            <#if field.nameHump == 'sort'>
        ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>

        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());

        List<${Domain}DTO> ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}DTO.class);
        pageDto.setList(${domain}DtoList);
    }


    /**
     * 保存
     */
    public void save${Domain}(${Domain}DTO ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        Date nowDate = new Date();
        if (StringUtils.isEmpty(${domain}Dto.getId())) {
            ${domain}.setId(UuidUtil.getShortUuid());
            <#list fieldList as field>
                <#if field.nameHump == 'createdAt'>
            ${domain}.setCreatedAt(nowDate);
                </#if>
                <#if field.nameHump == 'updatedAt'>
            ${domain}.setUpdatedAt(nowDate);
                </#if>
            </#list>
            ${domain}Mapper.insert(${domain});
        } else {
            <#list fieldList as field>
                <#if field.nameHump == 'updatedAt'>
            ${domain}.setUpdatedAt(nowDate);
                </#if>
            </#list>
            ${domain}Mapper.updateByPrimaryKey(${domain});
        }
    }


    /**
     * 单个查询
     */
    public ${Domain}DTO get${Domain}(String id) {
        ${Domain} ${domain} = ${domain}Mapper.selectByPrimaryKey(id);
        return CopyUtil.copy(${domain}, ${Domain}DTO.class);
    }

    /**
     * 删除
     */
    public void delete${Domain}(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
