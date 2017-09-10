package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoodsRepos extends MongoRepository<Goods, String> {


}
