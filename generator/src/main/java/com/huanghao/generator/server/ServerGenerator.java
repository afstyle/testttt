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


    static String toServicePath = "server\\src\\main\\java\\com\\huanghao\\server\\service\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Domain", Domain);
        map.put("domain", domain);


        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

    }
}
