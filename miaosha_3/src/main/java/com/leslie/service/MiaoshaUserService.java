package com.leslie.service;

import com.leslie.dao.MiaoshaUserDao;
import com.leslie.domain.MiaoshaUser;
import com.leslie.exception.GlobalException;
import com.leslie.result.CodeMsg;
import com.leslie.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Leslie
 * @create 2021/6/11 16:13
 */
@Service
public class MiaoshaUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        return true;
    }
}
