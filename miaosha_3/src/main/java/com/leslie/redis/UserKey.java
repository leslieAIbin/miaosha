package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/10 22:27
 */
public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
