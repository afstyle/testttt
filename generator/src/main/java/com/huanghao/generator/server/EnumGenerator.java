package com.huanghao.generator.server;


import com.huanghao.server.util.GetClassFromPackageUtil;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HuangHao
 * @date 2020/8/20 22:19
 */
public class EnumGenerator {

    static String path = "admin\\src\\utils\\enums.js";
    static String enumPackageName = "com.huanghao.server.enums.commons";

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        long begin = System.currentTimeMillis();
        try {
            System.out.println("生成前端Enums文件 - go");
            List<Class> classList = GetClassFromPackageUtil.getClassFromPackage(enumPackageName);
            for (Class clazz : classList) {
                System.out.println("获取Enum对象：" + clazz.getName());
                toJson(clazz, buffer);
            }
            writeJs(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("生成前端Enums文件 - end");
        System.out.println("执行耗时:" + (end - begin) + " 毫秒");
    }

    private static void toJson(Class clazz, StringBuffer buffer) throws Exception {
        String key = toUnderline(clazz.getSimpleName());
        toJson(clazz, key, buffer);
    }

    private static void toJson(Class clazz, String key, StringBuffer buffer) throws Exception {
        Object[] objects = clazz.getEnumConstants();
        Method name = clazz.getMethod("name");
        Method getLabel = clazz.getMethod("getLabel");
        Method getValue = clazz.getMethod("getValue");

        // 生成数组
        buffer.append(key).append(" = [");
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            if (getValue == null) {
                buffer.append("{value:\"").append(name.invoke(obj)).append("\", label:\"").append(getLabel.invoke(obj)).append("\"}");
            } else {
                buffer.append("{value:\"").append(getValue.invoke(obj)).append("\", label:\"").append(getLabel.invoke(obj)).append("\"}");
            }
            if (i < objects.length - 1) {
                buffer.append(",");
            }
        }
        buffer.append("];\r\n");
    }

    /**
     * 写文件
     * @param stringBuffer
     */
    public static void writeJs(StringBuffer stringBuffer) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
//            System.out.println(path);
            osw.write(stringBuffer.toString());
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 功能：驼峰转大写下划线，并去掉_ENUM
     * 如：SectionChargeEnum 变成 SECTION_CHARGE
     * @param str
     * @return
     */
    public static String toUnderline(String str) {
        String result = underline(str).toString();
        return result.substring(1, result.length()).toUpperCase().replace("_ENUM", "");
    }

    /**
     * 驼峰转下划线，第一位是下划线
     * 如：SectionChargeEnum 变成 _section_charge_enum
     * @param str
     * @return
     */
    private static StringBuffer underline(String str) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        }else {
            return sb;
        }
        return underline(sb.toString());
    }
}
