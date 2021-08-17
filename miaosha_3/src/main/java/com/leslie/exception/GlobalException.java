package com.leslie.exception;

import com.leslie.result.CodeMsg;

/**
 * @author Leslie
 * @create 2021/6/11 20:01
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
