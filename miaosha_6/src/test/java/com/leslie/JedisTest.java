package com.leslie;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author Leslie
 * @create 2021/8/10 16:32
 */
public class JedisTest {

    @Test
    public void testJedis() throws Exception{

        //创建一个连接Jedis对象，参数:host、port
        Jedis jedis=new Jedis("127.0.0.1",6379);
        //直接使用Jedis操作Redis，所有Jedis命令都对应一个方法
//        jedis.auth("123456");
        jedis.set("test123","my first jedis test");

        String string = jedis.get("test123");
        System.out.println(string);
        //关闭连接
        jedis.close();
    }
}
