package com.deepexi.a.service;

import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

public interface ComponentsService {

    PageBean getComponentsList(Integer page, Integer size, ComponentsQuery query);

    Components getComponentsById(String id);

    Integer createComponents(Components components);

    Boolean deleteComponentsByIds(List<String> ids);

    Integer updateComponents(Components components);

    void testError();
}
