package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/8/10 16:35
 */
public class MiaoshaUserKey extends BasePrefix{
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;       // token 过期时间, 2天
    private MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");
}
