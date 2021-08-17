package com.leslie.controller;

import com.leslie.domain.Goods;
import com.leslie.domain.MiaoshaOrder;
import com.leslie.domain.MiaoshaUser;
import com.leslie.domain.OrderInfo;
import com.leslie.result.CodeMsg;
import com.leslie.result.Result;
import com.leslie.service.*;
import com.leslie.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    /**
     * GET  和 POST
     *  浏览器传参有区别
     *  Get 是幂等的 从服务端获取数据
     *  POST 可以更改服务端数据
     **/

    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> list(Model model, MiaoshaUser user,
                       @RequestParam("goodsId") long goodsId){
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        // 判断库存，库存 < 0 则直接返回
        // 防止超卖 SQL 加库存数量判断：防止库存变为负数
        // 数据库加唯一索引： 防止用户重复购买
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId); //10个商品，req1 req2
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        // 判断是否已经秒杀到了，秒杀订单暂存于 redis
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        return Result.success(orderInfo);
    }
}
