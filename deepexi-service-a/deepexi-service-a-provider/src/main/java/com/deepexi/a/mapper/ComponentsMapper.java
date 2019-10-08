package com.deepexi.a.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by donh on 2018/7/24.
 */
public interface ComponentsMapper extends BaseMapper<Components> {

    List<Components> selectPageVo(@Param(Constants.WRAPPER) Wrapper<Components> wrapper);
}
