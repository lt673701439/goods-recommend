package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 */
@Data
@Document(collection = "brands")
@EqualsAndHashCode(callSuper = false)
public class Brands {

    private String id;

    private String ename;

    private String name;

    @Field(value = "video_url")
    private String videoUrl;

    private String area;

    private String image;

    @Field(value = "short_desc")
    private String shortDesc;

    @Field(value = "video_image_url")
    private String videoImageUrl;


}
