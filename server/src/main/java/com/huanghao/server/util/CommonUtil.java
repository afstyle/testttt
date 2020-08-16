package com.huanghao.server.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author HuangHao
 * @date 2020/8/16 17:49
 */
public class CommonUtil {

    public static List<String> splitString2List(String string) {
        if (StringUtils.isEmpty(string)) {
            return new ArrayList<>();
        }
        String[] splits = string.split(",");
        List<String> result = new ArrayList<String>(splits.length);
        Collections.addAll(result, splits);
        return result;
    }


}
