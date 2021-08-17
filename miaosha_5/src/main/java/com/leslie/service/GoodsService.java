package com.leslie.service;

import com.leslie.dao.GoodsDao;
import com.leslie.domain.MiaoshaGoods;
import com.leslie.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leslie
 * @create 2021/8/11 19:15
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        // 减库存操作依赖数据库去完成，将 "卖超问题" 交给数据库层面的乐观锁去控制
        goodsDao.reduceStock(g);
    }


}
