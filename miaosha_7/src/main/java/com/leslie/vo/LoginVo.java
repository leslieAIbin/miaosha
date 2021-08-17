package com.leslie.vo;

import com.leslie.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Leslie
 * @create 2021/8/10 21:08
 */
@Data
public class LoginVo {

    /**
     * 验证器 可以自定义验证器 IsMobile
     **/
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
