1. 解决 optimus-commons 依赖报错
    - git clone deepexi/optimus-commons
    - mvn install到本地
    - 修改 springcloud-exam 中的依赖版本为 1.1.6-SNAPSHOT


2. 
    - 添加服务注册中心 eureka-server 模块 
    - 注释 apollo配置中心 相关配置（避免因为找不到配置中心服务而启动过慢和报错）

3. 升级 mybatis-plus 到3.0.7，使用 Wrapper 自定义SQL 简化xml中查询条件的代码

3. 启动服务消费方B的时候出现异常
```
Body parameters cannot be used with form parameters
```

分析原因：Feign中会对接口参数校验，接口定义中如果url字符串中没有@PathVariable 对应的占位符，则接口会将PathVariable变量视为Form参数，但是表单参数与@RequestBody不能一起使用。
**此处因为在url上的PathVariable变量使用了正则表达式，将其去除问题解决。**


4. 组件分类(category) 和 组件状态(status) 字段数据库中存int值，使用枚举类(放在 com.deepexi.a.domain.enums )转为对应文字显示在前端


5. 在消费方添加配置，解决服务调用超时异常导致重复执行请求的问题

```
feign.client.config.default.connectTimeout=1000
feign.client.config.default.readTimeout=12000
```

6. 服务提供方重启后，服务发现时间过长，在服务消费方配置为5s（默认30s）

```
eureka.client.fetchRegistry=true
eureka.client.registry-fetch-interval-seconds=5
```



7. feign声明式调用中，对同一个url不同请求方法（Get和Post）的服务调用错误：

```
@GetMapping("/deepexi-service-a/api/v1/components")
    Payload getComponentsList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                              ComponentsQuery query
);
@PostMapping("/deepexi-service-a/api/v1/components")
Payload createComponents(@RequestBody Components components);
```

以上测试中，消费方对getComponentsList()的调用到提供方变成了createComponents()


- 原因：

feign消费服务时，以POST方式请求的接口声明中 **有且只有一个参数前为@RequestBody，其余参数前必须有@RequestParam。如果参数前什么也不写，那么默认是由@RequestBody修饰的**

- 解决办法：

Spring Cloud 2.1.0以上的OpenFeign提供@SpringQueryMap解决Feign多参请求问题 ,使用该注解，可以将一个POJO或者Map作为一个GET请求的参数query parameter map

所以
- 升级 spring-cloud-dependencies 为 Greenwich.RELEASE
- 在getComponentsList方法的query参数前加上@SpringQueryMap即可
```
@GetMapping("/deepexi-service-a/api/v1/components")
    Payload getComponentsList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                              @SpringQueryMap ComponentsQuery query
);
@PostMapping("/deepexi-service-a/api/v1/components")
Payload createComponents(@RequestBody Components components);
```
