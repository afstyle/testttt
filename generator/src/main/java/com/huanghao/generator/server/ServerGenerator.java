package com.huanghao.generator.server;

import com.huanghao.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HuangHao
 * @date 2020/8/20 22:19
 */
public class ServerGenerator {

    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\com\\huanghao\\server\\service\\";
    static String toControllerPath = String.format("%s\\src\\main\\java\\com\\huanghao\\%s\\controller\\admin\\", MODULE, MODULE);

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);


        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);

    }
}
