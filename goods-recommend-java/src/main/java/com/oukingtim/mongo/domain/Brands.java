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
@Document(collection = Constants.Mongo.COLLECTION_BRANDS)
@EqualsAndHashCode(callSuper = false)
public class Brands {

    @Field(value = "id")
    private String brandsId;

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
