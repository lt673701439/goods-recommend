package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.domain.GoodsEvents;

import java.util.List;
import java.util.Map;

public interface GoodsEventsService {

    List<GoodsEvents> getAllGoodsEventsList();

    GoodsEvents getByGoodsEventsId(String goodsEventsId);

    List<GoodsEvents> getGoodsEventsByCondition(Map<String, Object> map);

    Long getGoodsEventsCount(String date);
}
