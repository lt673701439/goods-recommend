package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.GoodsEvents;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoodsEventsRepos extends MongoRepository<GoodsEvents, String> {

    GoodsEvents getByGoodsEventsId(String goodsEventId);
}
