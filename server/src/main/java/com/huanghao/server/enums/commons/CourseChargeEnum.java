package com.huanghao.server.enums.commons;

public enum CourseChargeEnum {

    CHARGE("C", "收费"),
    FREE("F", "免费"),
    ;

    private String value;

    private String label;

    CourseChargeEnum(String value, String label) {
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
