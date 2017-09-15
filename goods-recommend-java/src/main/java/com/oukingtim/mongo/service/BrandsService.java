package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Brands;

import java.util.List;
import java.util.Map;

public interface BrandsService {

    List<Brands> getForPageList(int pageNumber, int pageSize, String sortType);

    Brands getByBrandsId(String brandsId);

    List<Brands> getBrandsByCondition(Map<String, Object> map);

    Long getBrandsCount();
}
