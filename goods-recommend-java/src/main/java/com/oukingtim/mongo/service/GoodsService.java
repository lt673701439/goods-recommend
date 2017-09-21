package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    List<Goods> getForPageList(int pageNumber, int pageSize, String sortType);

    Goods getByGoodsId(String goodsId);

    List<Goods> getGoodsByCondition(Map<String,Object> map);

    Long getGoodsCount(String date);

    List<Goods> getGoodsByDate(String startDate,String endDate,int pageNumber,int pageSize,String sortType);

    List<Goods> getGoodsByBrandId(String brandId,int pageNumber, int pageSize, String sortType);

    List<Goods> getGoodsBySellerId(String sellerId,int pageNumber, int pageSize, String sortType);

    List<Goods> getGoodsByCategoryId(String categoryId,int pageNumber,int pageSize,String sortType);

}
