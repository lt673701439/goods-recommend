package com.oukingtim.mongo.domain;

import com.oukingtim.util.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 */
@Data
@Document(collection = Constants.Mongo.COLLECTION_USERS)
@EqualsAndHashCode(callSuper = false)
public class Users {

    @Field(value = "id")
    private String usersId;

    private String image;

    @Field(value = "discoverys_total")
    private Integer discoverysTotal;

    private String nickname;

    @Field(value = "fans_total")
    private Integer fansTotal;

}
