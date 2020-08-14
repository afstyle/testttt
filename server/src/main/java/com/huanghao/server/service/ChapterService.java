package com.huanghao.server.service;

import com.huanghao.server.domain.Chapter;
import com.huanghao.server.domain.ChapterExample;
import com.huanghao.server.dto.ChapterDto;
import com.huanghao.server.mapper.ChapterMapper;
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

    public List<ChapterDto> list() {
        ArrayList<ChapterDto> chapterDtoList = new ArrayList<>();
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
