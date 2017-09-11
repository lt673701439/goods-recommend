package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GoodsRepos extends MongoRepository<Goods, String> {


    List<Goods> getGoodsByBrandId(String brandId);
}
