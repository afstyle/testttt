package com.huanghao.generator.util;

/**
 * @author HuangHao
 * @date 2020/9/12 22:17
 */
public class EnumDto {

    private String name;
    private String value;
    private String label;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "EnumDto{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public EnumDto() {
    }

    public EnumDto(String name, String value, String label) {
        this.name = name;
        this.value = value;
        this.label = label;
    }
}
