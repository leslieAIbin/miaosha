package com.leslie.miaosha.controller;

import com.leslie.miaosha.result.CodeMsg;
import com.leslie.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;

/**
 * @author Leslie
 * @create 2021/5/18 9:54
 */
@Controller
@RequestMapping
public class DemoController {


    /**
     * REST api json 格式输出
     */
    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "hello world";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello, leslie");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    /**
     * 集成 thymeleaf 做页面模板
     **/
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model  model){
        model.addAttribute("model_name", "fly_away");
        return "hello";
    }


}
