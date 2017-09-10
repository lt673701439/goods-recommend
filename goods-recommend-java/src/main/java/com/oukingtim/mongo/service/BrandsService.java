package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.domain.MongoTest;

import java.util.List;
import java.util.Map;

public interface BrandsService {

    List<Brands> getAllBrandsList();

    Brands getBrandsById(String id);

    List getBrandsByCondition(Map<String, Object> map);
}
