package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.MongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GetGoodsRepos extends MongoRepository<MongoTest, String> {

}
