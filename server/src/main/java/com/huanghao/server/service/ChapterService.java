package com.huanghao.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huanghao.server.domain.Chapter;
import com.huanghao.server.domain.ChapterExample;
import com.huanghao.server.dto.ChapterDto;
import com.huanghao.server.dto.PageDto;
import com.huanghao.server.mapper.ChapterMapper;
import com.huanghao.server.util.CopyUtil;
import com.huanghao.server.util.UuidUtil;
import org.springframework.beans.BeanUtils;
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

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

        ArrayList<ChapterDto> chapterDtoList = (ArrayList<ChapterDto>) CopyUtil.copyList(chapterList, ChapterDto.class);

        pageDto.setList(chapterDtoList);
    }

    public void save(ChapterDto chapterDto) {
        chapterDto.setId(UuidUtil.getShortUuid());
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        chapterMapper.insert(chapter);
    }
}
