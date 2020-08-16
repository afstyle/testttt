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

    public void list(PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

        ArrayList<ChapterDTO> chapterDtoList = (ArrayList<ChapterDTO>) CopyUtil.copyList(chapterList, ChapterDTO.class);

        pageDto.setList(chapterDtoList);
    }

    public void save(ChapterDTO chapterDto) {
        chapterDto.setId(UuidUtil.getShortUuid());
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        chapterMapper.insert(chapter);
    }
}
