package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Brands;

import java.util.List;
import java.util.Map;

public interface BrandsService {

    List<Brands> getAllBrandsList();

    Brands getBrandsById(String id);

    List<Brands> getBrandsByCondition(Map<String, Object> map);

    Long getBrandsCount();
}
