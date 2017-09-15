package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Sellers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellersRepos extends MongoRepository<Sellers, String> {

    Sellers getBySellersId(String sellersId);
}
