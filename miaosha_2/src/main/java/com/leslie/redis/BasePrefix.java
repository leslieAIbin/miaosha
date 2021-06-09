package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/9 11:30
 */
public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;      // 过期时间, 默认0代表永不过期
    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }


    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
