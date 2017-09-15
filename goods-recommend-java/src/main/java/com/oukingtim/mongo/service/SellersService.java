package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Sellers;

import java.util.List;
import java.util.Map;

public interface SellersService {

    List<Sellers> getAllSellersList();

    Sellers getBySellersId(String sellersId);

    List<Sellers> getSellersByCondition(Map<String, Object> map);

    Long getSellersCount();
}
