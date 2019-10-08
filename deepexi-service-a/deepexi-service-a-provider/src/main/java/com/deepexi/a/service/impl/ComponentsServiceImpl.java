package com.deepexi.a.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import com.deepexi.a.service.ComponentsService;
import com.deepexi.a.extension.ApplicationException;
import com.deepexi.a.mapper.ComponentsMapper;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by donh on 2018/11/6.
 */
@Service
public class ComponentsServiceImpl implements ComponentsService {

    private static final Logger logger = LoggerFactory.getLogger(ComponentsServiceImpl.class);

    @Autowired
    private ComponentsMapper componentsMapper;

    public PageBean getComponentsList(Integer page, Integer size, ComponentsQuery query) {
        PageHelper.startPage(page, size);
        final LambdaQueryWrapper<Components> qw = new QueryWrapper<Components>()
                .lambda()
                .like(StringUtils.isNotBlank(query.getName()), Components::getName, query.getName())
                .eq(query.getCategory() != null, Components::getCategory, query.getCategory())
                .eq(query.getStatus() != null, Components::getStatus, query.getStatus());
        List<Components> userTasks = componentsMapper.selectPageVo(qw);
        return new PageBean<>(userTasks);
    }

    public Integer createComponents(Components components) {
        return componentsMapper.insert(components);
    }

    public Boolean deleteComponentsByIds(List<String> ids) {
        componentsMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public Integer updateComponents(Components components) {
        return componentsMapper.updateById(components);
    }

    @SentinelResource(value = "testSentinel", fallback = "doFallback", blockHandler = "exceptionHandler")
    public Components getComponentsById(String id) {
        return componentsMapper.selectById(id);
    }

    public String doFallback(long i) {
        // Return fallback value.
        return "Oops, degraded";
    }

    /**
     * 熔断降级处理逻辑
     *
     * @param s
     * @param ex
     * @return
     */
    public void exceptionHandler(long s, Exception ex) {
        // Do some log here.
        logger.info("-------------熔断降级处理逻辑---------\n");
        throw new ApplicationException("100", "熔断降级处理");
    }

    @Override
    public void testError() {
        throw new ApplicationException("100", "测试异常");
    }
}
