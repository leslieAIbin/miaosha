package com.leslie.domain;


import lombok.Data;

import java.util.Date;

/**
 * @author Leslie
 * @create 2021/6/11 16:08
 */
@Data
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
