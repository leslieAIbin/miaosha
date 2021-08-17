package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/8/10 16:34
 */

public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
