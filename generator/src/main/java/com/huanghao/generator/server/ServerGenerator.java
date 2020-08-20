package com.huanghao.generator.server;

import com.huanghao.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author HuangHao
 * @date 2020/8/20 22:19
 */
public class ServerGenerator {


    static String toPath = "generator\\src\\main\\java\\com\\huanghao\\generator\\test\\";

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.generator(toPath + "Test.java");

    }
}
