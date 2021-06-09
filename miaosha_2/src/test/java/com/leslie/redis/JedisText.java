package com.leslie.redis;

/**
 * @author Leslie
 * @create 2021/6/9 9:06
 */

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 *Jedis的测试方法
 **/
public class JedisText {

    @Test
    public void testJedis() throws Exception{

        //创建一个连接Jedis对象，参数:host、port
        Jedis jedis=new Jedis("192.168.150.129",6379);
        //直接使用Jedis操作Redis，所有Jedis命令都对应一个方法
        jedis.auth("123456");
        jedis.set("test123","my first jedis test");
        String string = jedis.get("test123");
        System.out.println(string);
        //关闭连接
        jedis.close();
    }


}
