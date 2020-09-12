package com.huanghao.generator.server;


import com.huanghao.generator.util.DbUtil;
import com.huanghao.generator.util.EnumDto;
import com.huanghao.generator.util.Field;
import com.huanghao.generator.util.FreemarkerUtil;
import com.huanghao.server.util.GetClassFromPackageUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author HuangHao
 * @date 2020/8/20 22:19
 */
public class EnumGenerator {

    static String toJsPath = "admin\\src\\utils\\enums.js";
    static String toJavaPath = "server\\src\\main\\java\\com\\huanghao\\server\\enums\\commons\\";
    static String enumPackageName = "com.huanghao.server.enums.commons";
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        long begin = System.currentTimeMillis();
        try {
            // 先执行java文件生成
            System.out.println("生成后端Enums文件 - go");
            writeJavaEnums();
            System.out.println("生成后端Enums文件 - end");

            // 再根据java生成的enum信息来生成js
            System.out.println("生成前端Enums文件 - go");
            List<Class> classList = GetClassFromPackageUtil.getClassFromPackage(enumPackageName);
            for (Class clazz : classList) {
                System.out.println("获取Enum对象：" + clazz.getName());
                toJson(clazz, buffer);
            }
            writeJs(buffer);
            System.out.println("生成前端Enums文件 - end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("前后端enum文件生成完毕，执行耗时:" + (end - begin) + " 毫秒");
    }

    private static void writeJavaEnums() throws Exception {
        // 只生成配置文件中的第一个table节点
        File file = new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc=reader.read(file);
        //获取xml文件的根节点
        Element rootElement=doc.getRootElement();
        //读取context节点
        Element contextElement = rootElement.element("context");
        //定义一个Element用于遍历
        Element tableElement;
        //取第一个“table”的节点
        tableElement = contextElement.elementIterator("table").next();
        String tableName = tableElement.attributeValue("tableName");

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);

        if (CollectionUtils.isEmpty(fieldList)) {
            return;
        }

        fieldList = fieldList.stream()
                .filter(field -> !StringUtils.isEmpty(field.getEnumsComment()) && !StringUtils.isEmpty(field.getEnumsName()))
                .collect(Collectors.toList());
        for (Field field : fieldList) {
            List<EnumDto> enumDtoList = toEnumDtoList(field);
            String enumName = field.getEnumsName();

            Map<String, Object> map = new HashMap<>();
            map.put("enumList", enumDtoList);
            map.put("enumName", enumName);

            // Enum
            FreemarkerUtil.initConfig("enum.ftl");
            FreemarkerUtil.generator(toJavaPath + enumName + ".java", map);
        }



    }

    /**
     * 例如：ONE("1", "初级")|TWO("2", "中级")|THREE("3", "高级")
     * @param field 主要是为了获取EnumsComment
     * @return 已转变过的EnumDtoList
     */
    private static List<EnumDto> toEnumDtoList(Field field) {
        List<EnumDto> result = new ArrayList<>();

        String enumsComment = field.getEnumsComment();
        String[] splitArray = enumsComment.split("\\|");
        for (String item : splitArray) {
            int nameStart = item.indexOf("(");
            String name = item.substring(0, nameStart).toUpperCase();
            int valueStart = nameStart + 2;
            int valueEnd = item.indexOf(",") - 1;
            String value = item.substring(valueStart, valueEnd);
            int labelStart = item.lastIndexOf("\"") + 1;
            String label = item.substring(labelStart);
            EnumDto enumDto = new EnumDto(name, value, label);
            result.add(enumDto);
        }

        return result;
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
            out = new FileOutputStream(toJsPath);
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
