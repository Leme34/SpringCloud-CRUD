package com.deepexi.a.domain.enums;

/**
 * Created by lsd
 * 2019-10-07 21:18
 */
public enum ComponentStatusEnum {

    SHELF(0,"上架"),
    OBTAINED(1,"下架");

    private Integer value;

    ComponentStatusEnum(Integer value, String desc) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
