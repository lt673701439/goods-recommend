package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Brands;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandsRepos extends MongoRepository<Brands, String> {

    Brands getByBrandsId(String brandsId);
}
