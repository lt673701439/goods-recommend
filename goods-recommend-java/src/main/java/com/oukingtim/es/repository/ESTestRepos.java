package com.oukingtim.es.repository;

import com.oukingtim.es.domain.ESTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("elasticTestRepository")
public interface ESTestRepos extends ElasticsearchRepository<ESTest,String> {

    List<ESTest> findByName(String name);
}
