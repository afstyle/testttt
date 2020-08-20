package com.huanghao.generator.server;

import com.huanghao.generator.util.DbUtil;
import com.huanghao.generator.util.Field;
import com.huanghao.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

/**
 * @author HuangHao
 * @date 2020/8/20 22:19
 */
public class ServerGenerator {

    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\com\\huanghao\\server\\service\\";
    static String toDTOPath = "server\\src\\main\\java\\com\\huanghao\\server\\dto\\";
    static String toControllerPath = String.format("%s\\src\\main\\java\\com\\huanghao\\%s\\controller\\admin\\", MODULE, MODULE);

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";

        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", MODULE);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);


        // dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDTOPath + Domain + "DTO.java", map);

        // service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        //controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);

    }


    /**
     * 获取所有的Java类型，使用Set去重
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
