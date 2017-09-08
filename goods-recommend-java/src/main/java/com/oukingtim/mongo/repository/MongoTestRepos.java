package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.MongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 测试mongo
 * 继承MongoRepository 方法有一些基本的CRUD方法
 * 注入MongoTemplate有更多的操作方法
 */
public interface MongoTestRepos extends MongoRepository<MongoTest, String> {

    MongoTest findByName(String name);
}
