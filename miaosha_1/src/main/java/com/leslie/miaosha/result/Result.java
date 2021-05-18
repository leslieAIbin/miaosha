package com.leslie.miaosha.result;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import lombok.Data;

/**
 * @author Leslie
 * @create 2021/5/18 10:18
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    // 一个泛型方法
    // 返回值类型定义前的<T>是必须的，用来声明一个类型持有者名称，
    // 然后就可以把T当作一个类型代表来声明成员、参数和返回值类型。
    public static <T> Result<T> success(T data){return new Result<T>(data);}
    public static <T> Result<T> error(CodeMsg cm){
        return new  Result<T>(cm);
    }
    /*
     * 构造函数私有，禁止外部创建对象
     **/

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }
    private Result(CodeMsg cm){
        if(cm == null) return ;
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

}
