package com.deepexi.a.domain.enums;

/**
 * Created by lsd
 * 2019-10-07 21:18
 */
public enum ComponentCategoryEnum {

    SHELF(0,"基础设施"),
    OBTAINED(1,"业务中台");

    private Integer value;

    ComponentCategoryEnum(Integer value, String desc) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
