package com.leslie.controller;

import com.leslie.domain.Goods;
import com.leslie.domain.MiaoshaOrder;
import com.leslie.domain.MiaoshaUser;
import com.leslie.domain.OrderInfo;
import com.leslie.result.CodeMsg;
import com.leslie.service.*;
import com.leslie.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Leslie
 * @create 2021/8/12 15:40
 */

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;


    /**
     *  初始吞吐量： 1,413.348  5000并发 10次
     **/

    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user,
                       @RequestParam("goodsId") long goodsId){
        model.addAttribute("user", user);
        if(user == null){
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否秒杀到了 防止同一个用户秒杀
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null){
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存 下订单 写入秒杀订单  原子的操作
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";

    }

}
