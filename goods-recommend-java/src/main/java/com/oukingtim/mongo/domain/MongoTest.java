package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * mongodb测试实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MongoTest {
    private String id;

    private String name;

    private int age;


}
