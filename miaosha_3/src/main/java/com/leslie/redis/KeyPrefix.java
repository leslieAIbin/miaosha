package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/10 21:28
 */
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
