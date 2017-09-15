package com.oukingtim.es.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@EqualsAndHashCode
@Document(indexName = "elastic", type = "estest", shards = 1, replicas = 0, refreshInterval = "-1")
public class ESTest {
    @Id
    private String id;

    private String name;

    private String age;

    private String shortName;
}
