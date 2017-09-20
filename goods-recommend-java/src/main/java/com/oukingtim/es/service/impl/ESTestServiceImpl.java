package com.oukingtim.es.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oukingtim.es.domain.ESTest;
import com.oukingtim.es.repository.ESTestRepos;
import com.oukingtim.es.service.ESTestService;
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
import java.util.List;

@Service("eSTestService")
public class ESTestServiceImpl implements ESTestService{

    @Autowired
    private ESTestRepos esTestRepos;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Value("spring.data.elasticsearch.index.goods")
    private String index;

    @Value("spring.data.elasticsearch.type.goods")
    private String type;

    @Override
    public List<ESTest> getAll() {
        List<ESTest> list = new ArrayList<>();
        for (ESTest esTest : esTestRepos.findAll()) {
            list.add(esTest);
            System.out.println(esTest);
        }
        return list;
    }

    @Override
    public List<ESTest> searchESTest(String keyWord) {
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(index)
                .setTypes("estest")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.multiMatchQuery(
                        keyWord,
                        "short_name", "name"
                ))
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        SearchHits hits = response.getHits();
        List<ESTest> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (SearchHit i : hits) {
            try {
                ESTest esTest = mapper.readValue(i.sourceAsString(), ESTest.class);
                list.add(esTest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public ESTest getById(String id) {
        return esTestRepos.findOne(id);
    }

    @Override
    public List<ESTest> getByName(String name) {
        return esTestRepos.findByName(name);
    }

    @Override
    public void delete(String id) {
        esTestRepos.delete(id);
    }

    @Override
    public void deleteAll() {
        esTestRepos.deleteAll();
    }
}
