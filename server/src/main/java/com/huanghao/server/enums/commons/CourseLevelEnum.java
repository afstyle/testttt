package com.huanghao.server.enums.commons;

public enum CourseLevelEnum {

    ONE("1", "初级"),
    TWO("2", "中级"),
    THREE("3", "高级"),
    ;

    private String value;

    private String label;

    CourseLevelEnum(String value, String label) {
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
