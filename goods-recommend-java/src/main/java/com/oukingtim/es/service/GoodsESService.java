package com.oukingtim.es.service;

import com.oukingtim.mongo.domain.Goods;

import java.util.HashMap;
import java.util.List;

public interface GoodsESService {

    List<HashMap> searchGoods(String keyWord);

}
