package com.huanghao.server.enums.commons;

public enum CourseStatusEnum {

    PUBLISH("P", "发布"),
    DRAFT("D", "草稿");

    private String value;

    private String label;

    CourseStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
