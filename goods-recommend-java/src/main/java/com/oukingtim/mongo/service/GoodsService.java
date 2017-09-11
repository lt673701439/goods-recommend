package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    List<Goods> getAllGoodsList();

    Goods getGoodsById(String id);

    List<Goods> getGoodsByCondition(Map<String,Object> map);

    Long getGoodsCount();

    List<Goods> getGoodsByBrandId(String brandId);
}
