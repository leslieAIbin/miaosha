package com.leslie.redis;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Leslie
 * @create 2021/6/8 19:17
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
