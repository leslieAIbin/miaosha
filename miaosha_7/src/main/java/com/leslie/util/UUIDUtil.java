package com.leslie.util;

import java.util.UUID;

/**
 * @author Leslie
 * @create 2021/8/11 15:05
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
