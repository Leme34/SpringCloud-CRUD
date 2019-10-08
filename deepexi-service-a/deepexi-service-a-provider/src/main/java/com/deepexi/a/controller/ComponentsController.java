package com.deepexi.a.controller;

import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import com.deepexi.a.service.ComponentsService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lsd on 2019/10/7.
 */
@Api(value = "componentscontroller", description = "组件管理服务提供方")
@RestController
@RequestMapping("/api/v1/components")
public class ComponentsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ComponentsService componentsService;

    @ApiOperation(value = "过滤组件名称/分类/状态查询所有组件", notes = "", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页查询数", required = false, dataType = "Integer"),
    })
    @GetMapping
    public Payload getComponentsList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                     ComponentsQuery query) {
        return new Payload(componentsService.getComponentsList(page, size, query));
    }

    @GetMapping("/{id:[a-zA-Z0-9]+}")
    public Payload getComponentsById(@PathVariable("id") String id) {
        return new Payload(componentsService.getComponentsById(id));
    }

    @PostMapping
    public Payload createComponents(@RequestBody Components components) {
        return new Payload(componentsService.createComponents(components));
    }

    @PutMapping("/{id:[a-zA-Z0-9]+}")
    public Payload updateComponentsById(@PathVariable("id") String id, @RequestBody Components components) {
        components.setId(id);
        return new Payload(componentsService.updateComponents(components));
    }

    @DeleteMapping
    public Payload deleteComponentsByIds(@RequestBody List<String> ids) {
        return new Payload(componentsService.deleteComponentsByIds(ids));
    }

    @GetMapping("/testError")
    public Payload testError() {
        return new Payload(true);
    }

    @GetMapping("/testFeign")
    public Payload testFeign() {
        logger.info("远程调用成功: Hello World!!");
        return new Payload(true);
    }


}
