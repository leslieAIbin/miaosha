package com.leslie.exception;

import com.leslie.result.CodeMsg;
import lombok.Data;

/**
 * @author Leslie
 * @create 2021/8/11 14:25
 * 当出现异常情况时直接抛异常
 */
@Data
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
