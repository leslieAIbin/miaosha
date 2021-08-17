package com.leslie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Leslie
 * @create 2021/6/10 21:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")  // 读取配置文件中redis开头的
public class RedisConfig {
    private String host;
    private int port;
    private int timeout; //秒
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;//秒
}

