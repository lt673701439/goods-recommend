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
@Document(collection = Constants.Mongo.COLLECTION_SELLERS)
@EqualsAndHashCode(callSuper = false)
public class Sellers {

    @Field(value = "id")
    private String sellersId;

    private String logistics;

    @Field(value = "seller_id")
    private String sellerId;

    private String shopname;

    @Field(value = "video_url")
    private String videoUrl;

    private String image;

    @Field(value = "send_from")
    private String sendFrom;

    @Field(value = "public_name")
    private String publicName;

    private String role;

    @Field(value = "price_guarantee")
    private Boolean priceGuarantee;

    private String follow;

    @Field(value = "video_image_url")
    private String videoImageUrl;

    @Field(value = "dport_id")
    private String dportId;

}
