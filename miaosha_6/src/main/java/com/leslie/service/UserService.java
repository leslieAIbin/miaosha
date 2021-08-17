package com.leslie.service;

import com.leslie.dao.UserDao;
import com.leslie.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leslie
 * @create 2021/8/10 15:15
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    // 加上Transctional 保证在同一个事务当中
    @Transactional
    public boolean tx(){
        User u1= new User();
        u1.setId(4);
        u1.setName("2222");
        userDao.insert(u1);

        User u2= new User();
        u2.setId(1);
        u2.setName("11111");
        userDao.insert(u2);

        return true;
    }
}
