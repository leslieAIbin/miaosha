package com.leslie.result;

import lombok.Data;

/**
 * @author Leslie
 * @create 2021/6/10 20:52
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.data = data;
    }

    /**
     *  成功时候调用
     **/
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }
    /**
     *  失败时候的调用
     * */
    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>((T) codeMsg);
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
