package com.leslie.result;

/**
 * @author Leslie
 * @create 2021/8/9 15:30
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    public static <T> Result<T> error(CodeMsg cm){
        return new Result<>(cm);
    }


    /**
     *  构造函数私有， 禁止外部创建对象
     **/

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm){
        if(cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     *  不生成setter 方法， 禁止外部修改属性
     **/
    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
    public T getData(){
        return data;
    }
}
