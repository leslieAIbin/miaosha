package com.leslie.redis;


import lombok.Data;

/**
 * @author Leslie
 * @create 2021/6/10 21:29
 */
@Data
public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds; // 过期时间，默认0代表永不过期
    private String prefix;

    BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = 0;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
    }

    @Override
    public int expireSeconds(){
        return expireSeconds;
    }
    @Override
    public String getPrefix(){
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}
