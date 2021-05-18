package com.leslie.miaosha.result;

import lombok.Data;

/**
 * @author Leslie
 * @create 2021/5/18 9:58
 */

@Data
public class CodeMsg {
    private int code;
    private String msg;
    
    /**
     * 分模块定义返回状态
     **/

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "前端服务异常");

    //登录模块 5002XX

    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX

    /**
     *构造函数私有，禁止外部创建对象
     **/

    private CodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }



}
