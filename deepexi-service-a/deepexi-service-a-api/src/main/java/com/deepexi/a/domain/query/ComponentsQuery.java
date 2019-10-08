package com.deepexi.a.domain.query;

/**
 * Created by lsd
 * 2019-10-07 17:53
 */
public class ComponentsQuery {

    private String name;

    private Integer category;

    private String version;

    private Integer status;

    public ComponentsQuery() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
