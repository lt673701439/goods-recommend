package com.oukingtim.es.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oukingtim.es.service.GoodsESService;
import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.util.Constants;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsESServiceImpl implements GoodsESService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<HashMap> searchGoods(String keyWord) {
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(Constants.ES.INDEX)
                .setTypes(Constants.ES.TYPE_GOODS)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.multiMatchQuery(
                        keyWord,
                        "spu_name", "short_name", "name", "feature"
                ))
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        SearchHits hits = response.getHits();
        List<HashMap> list = new ArrayList<>();
        for (SearchHit i : hits) {
            try {
                Map map = i.sourceAsMap();
                HashMap hashMap = new HashMap();
                hashMap.put("goodsId",map.get("goodsId"));
                hashMap.put("insertDate",map.get("insertDate"));
                hashMap.put("brandId",map.get("brandId"));
                hashMap.put("spuName",map.get("spuName"));
                hashMap.put("price",map.get("price"));
                hashMap.put("name",map.get("name"));
                hashMap.put("shortName",map.get("shortName"));
                hashMap.put("feature",map.get("feature"));
                list.add(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
