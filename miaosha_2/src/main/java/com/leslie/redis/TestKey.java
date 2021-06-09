package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/9 11:38
 */
public class TestKey extends BasePrefix{
    public TestKey(String prefix) {
        super(prefix);
    }

    public static TestKey getByKey = new TestKey("key");
}
