package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods {

    private String id;

    private Double originalPrice;

    private String insertDate;

    private String timestamp;

    private String brandId;

    private List images;

    private String eventType;

    private String updateDate;

    private String originName;

    private String feature;

    private List cateGoryIds;

    private String type;

    private String image;

    private String stock;

    private Integer updateTime;

    private String shortName;

    private List syblings;

    private Double price;

    private String host;

    private Map retExgPolicy;

    private String desc;

    private String newName;

    private String sellerId;

    private String name;

    private Integer eventsCount;

    private String country;

    private Map shopping;

    private String itemType;

    private String categoryId;


}
