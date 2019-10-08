package com.deepexi.b.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import com.deepexi.b.extension.ApplicationException;
import com.deepexi.b.depend.ComponentsClient;

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
@Api(value = "componentscontroller", description = "组件管理服务消费方")
@RestController
@RequestMapping("/api/v1/components")
public class BComponentsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ComponentsClient componentsClient; // feign让跨服务调用能够看起来像本地调用


    @ApiOperation(value = "过滤组件名称/分类/状态查询所有组件", notes = "", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页查询数", required = false, dataType = "Integer"),
    })
    @GetMapping
    public Payload getComponentsList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                     ComponentsQuery query) {
        return componentsClient.getComponentsList(page, size, query);
    }

    @GetMapping("/{id:[a-zA-Z0-9]+}")
    public Payload getComponentsById(@PathVariable("id") String id) {
        return componentsClient.getComponentsById(id);
    }

    @PostMapping
    public Payload createComponents(@RequestBody Components Components) {
        return componentsClient.createComponents(Components);
    }

    @PutMapping("/{id:[a-zA-Z0-9]+}")
    public Payload updateComponentsById(@PathVariable("id") String id, @RequestBody Components components) {
        return componentsClient.updateComponentsById(id, components);
    }

    @DeleteMapping
    public Payload deleteComponentsById(@RequestBody List<String> ids) {
        return componentsClient.deleteComponentsByIds(ids);
    }

    @GetMapping("/testError")
    public Payload testError() {
        return componentsClient.testError();
    }

    @GetMapping("/testSentinel")
    @SentinelResource(value = "testSentinel", blockHandler = "exceptionHandler")
    public Payload testSentinel() {
        logger.info("远程Sentinel测试接口成功: Hello World!!");
        return new Payload(true);
    }

    /**
     * 熔断降级处理逻辑
     *
     * @param s
     * @param ex
     * @return
     */
    public Payload exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        logger.info("-------------熔断降级处理逻辑---------\n");
        throw new ApplicationException("100", "熔断降级处理");
    }

    @GetMapping("/testFeign")
    public Payload testFeign() {
        return componentsClient.testFeign();
    }
}
