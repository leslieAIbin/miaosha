package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/8/16 16:29
 */
public class MiaoshaKey extends BasePrefix{

    private MiaoshaKey(String prefix) {
        super(prefix);
    }
    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");
}
