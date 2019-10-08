package com.deepexi.a.api;

import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import com.deepexi.util.config.Payload;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lsd
 * 2019-10-07 18:59
 */
public interface ComponentsApi {

    @GetMapping("/deepexi-service-a/api/v1/components")
    Payload getComponentsList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                              @SpringQueryMap ComponentsQuery query
    );

    @GetMapping("/deepexi-service-a/api/v1/components/{id}")
    Payload getComponentsById(@PathVariable("id") String id);

    @PostMapping("/deepexi-service-a/api/v1/components")
    Payload createComponents(@RequestBody Components components);

    @PutMapping("/deepexi-service-a/api/v1/components/{id}")
    Payload updateComponentsById(@PathVariable("id") String id,@RequestBody Components components);

    @DeleteMapping("/deepexi-service-a/api/v1/components")
    Payload deleteComponentsByIds(@RequestBody List<String> ids);

    @GetMapping("/deepexi-service-a/api/v1/components/testError")
    Payload testError();

    @GetMapping("/deepexi-service-a/api/v1/components/testFeign")
    Payload testFeign();

}
