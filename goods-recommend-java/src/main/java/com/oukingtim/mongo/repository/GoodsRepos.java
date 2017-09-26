package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GoodsRepos extends MongoRepository<Goods, String> {

    Goods getByGoodsId(String goodsId);

    List<Goods> getGoodsByBrandId(String brandId, Pageable pageable);

    List<Goods> getGoodsBySellerId(String sellerId, Pageable pageable);

    Page<Goods> getGoodsByCategoryId(String categoryId, Pageable pageable);

    Page<Goods> getByNameLikeAndCountry(String name,String country, Pageable pageable);

    Long countByNameLikeAndCountry(String name,String country);

    Page<Goods> getByNameLike(String name, Pageable pageable);

    Long countByNameLike(String name);

}
