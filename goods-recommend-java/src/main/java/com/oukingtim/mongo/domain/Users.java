package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 */
@Data
@Document(collection = "users")
@EqualsAndHashCode(callSuper = false)
public class Users {

    private String image;

    @Field(value = "discoverys_total")
    private Integer discoverysTotal;

    private String nickname;

    private String id;

    @Field(value = "fans_total")
    private Integer fansTotal;

}
