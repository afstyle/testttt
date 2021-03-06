package com.huanghao.server.enums.commons;

public enum ${enumName} {

    <#list enumList as enum>
    ${enum.name}(${enum.value}, ${enum.label}),
    </#list>
    ;

    private String value;

    private String label;

    ${enumName}(String value, String label) {
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
