package com.deepexi.b.depend;

import com.deepexi.a.api.ComponentsApi;
import com.deepexi.a.domain.eo.Components;
import com.deepexi.a.domain.query.ComponentsQuery;
import com.deepexi.util.config.Payload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lsd on 2019/10/7.
 */
@FeignClient("deepexi-service-a-provider")
public interface ComponentsClient extends ComponentsApi {

}
