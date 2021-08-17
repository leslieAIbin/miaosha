package com.leslie.dao;

import com.leslie.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Leslie
 * @create 2021/8/10 15:09
 */
@Mapper
@Component
public interface MiaoshaUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    MiaoshaUser getById(@Param("id") long id);
}
