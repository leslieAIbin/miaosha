package com.leslie.controller;

import com.leslie.domain.User;
import com.leslie.redis.UserKey;
import com.leslie.result.Result;
import com.leslie.service.RedisService;
import com.leslie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Leslie
 * @create 2021/6/10 20:18
 */
@Controller
public class SampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    /**
     *  集成thyeleaf做页面模板
     **/
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("model_name", "fly_name");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 2, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(2);
        user.setName("222222");
        redisService.set(UserKey.getById, "" + 2, user);
        return Result.success(true);
    }
}
