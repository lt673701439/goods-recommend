package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    List<Goods> getForPageList(int pageNumber, int pageSize, String sortType);

    Goods getByGoodsId(String goodsId);

    List<Goods> getGoodsByCondition(Map<String,Object> map);

    Long getGoodsCount(String date);

    List<Goods> getGoodsByDate(String date);

    List<Goods> getGoodsByBrandId(String brandId);
}
