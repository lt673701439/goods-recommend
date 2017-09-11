package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Data
@Document(collection = "goods")
@EqualsAndHashCode(callSuper = false)
public class Goods {

    private String id;

    @Field(value = "brand_id")
    private Double originalPrice;

    @Field(value = "insert_date")
    private String insertDate;

    private String timestamp;

    @Field(value = "brand_id")
    private String brandId;

    private List images;

    @Field(value = "spu_name")
    private String spuName;

    @Field(value = "event_type")
    private String eventType;

    @Field(value = "update_date")
    private String updateDate;

    @Field(value = "origin_name")
    private String originName;

    private String feature;

    @Field(value = "category_ids")
    private List categoryIds;

    private String type;

    private String image;

    private String stock;

    @Field(value = "update_time")
    private Integer updateTime;

    @Field(value = "short_name")
    private String shortName;

    private List syblings;

    private Double price;

    private String host;

    @Field(value = "ret_exg_policy")
    private Map retExgPolicy;

    private String desc;

    @Field(value = "new_name")
    private String newName;

    @Field(value = "seller_id")
    private String sellerId;

    private String name;

    @Field(value = "events_count")
    private Integer eventsCount;

    private String country;

    private Map shopping;

    @Field(value = "item_type")
    private String itemType;

    @Field(value = "category_id")
    private String categoryId;


}
