package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/10 22:27
 */
public class TestKey extends BasePrefix{
    public TestKey(String prefix) {
        super(prefix);
    }

    public static TestKey getByKey = new TestKey("key");
}
