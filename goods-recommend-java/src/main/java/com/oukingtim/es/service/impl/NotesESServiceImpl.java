package com.oukingtim.es.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oukingtim.es.service.NotesESService;
import com.oukingtim.mongo.domain.Notes;
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
public class NotesESServiceImpl implements NotesESService{

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<HashMap> searchNotes(String keyWord) {
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(Constants.ES.INDEX)
                .setTypes(Constants.ES.TYPE_NOTES)
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
                hashMap.put("insertDate",map.get("insertDate"));
                hashMap.put("notesId",map.get("notesId"));
                hashMap.put("title",map.get("title"));
                hashMap.put("time",map.get("time"));
                hashMap.put("likes",map.get("likes"));
                hashMap.put("itemType",map.get("itemType"));
                list.add(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
