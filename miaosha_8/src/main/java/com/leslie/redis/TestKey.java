package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/8/10 16:36
 */
public class TestKey extends BasePrefix{
    public TestKey(String prefix) {
        super(prefix);
    }

    public static TestKey getByKey = new TestKey("key");
}
