package com.oukingtim.es.service.impl;

import com.oukingtim.es.service.NotesESService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotesESServiceImpl implements NotesESService{

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Value("${spring.data.elasticsearch.index.notes}")
    private String index;

    @Value("${spring.data.elasticsearch.type.notes}")
    private String type;

    @Override
    public List<HashMap> searchNotes(String keyWord) {
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.multiMatchQuery(
                        keyWord,
                        "title", "desc"
                ))
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        SearchHits hits = response.getHits();
        List<HashMap> list = new ArrayList<>();
        for (SearchHit i : hits) {
            try {
                Map map = i.sourceAsMap();
                HashMap hashMap = new HashMap();
                hashMap.put("insert_date",map.get("insertDate"));
                hashMap.put("id",map.get("notesId"));
                hashMap.put("title",map.get("title"));
                hashMap.put("time",map.get("time"));
                hashMap.put("likes",map.get("likes"));
                hashMap.put("item_type",map.get("itemType"));
                list.add(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
