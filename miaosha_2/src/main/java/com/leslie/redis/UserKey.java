package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/9 11:38
 */
public class UserKey extends BasePrefix{
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
