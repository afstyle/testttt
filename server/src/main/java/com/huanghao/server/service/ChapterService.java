package com.huanghao.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huanghao.server.domain.Chapter;
import com.huanghao.server.domain.ChapterExample;
import com.huanghao.server.dto.ChapterDTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.mapper.ChapterMapper;
import com.huanghao.server.util.CopyUtil;
import com.huanghao.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HuangHao
 * @date 2020/8/7 17:17
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public void listChapter(PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

        List<ChapterDTO> chapterDtoList = CopyUtil.copyList(chapterList, ChapterDTO.class);
        pageDto.setList(chapterDtoList);
    }

    public void saveChapter(ChapterDTO chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if (StringUtils.isEmpty(chapterDto.getId())) {
            chapter.setId(UuidUtil.getShortUuid());
            chapterMapper.insert(chapter);
        } else {
            chapterMapper.updateByPrimaryKey(chapter);
        }
    }

    public ChapterDTO getChapter(String id) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(id);
        return CopyUtil.copy(chapter, ChapterDTO.class);
    }

    public void deleteChapter(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }
}
