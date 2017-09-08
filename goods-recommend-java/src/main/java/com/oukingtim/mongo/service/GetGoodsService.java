package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.MongoTest;

import java.util.List;
import java.util.Map;

public interface GetGoodsService {

    List<MongoTest> getAllGoodsList();

    MongoTest getGoodsById(String id);

    List getGoodsByCondition(Map<String,Object> map);
}
