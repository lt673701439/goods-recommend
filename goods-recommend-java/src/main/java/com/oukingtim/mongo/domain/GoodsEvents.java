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
@Document(collection = "goods_events")
@EqualsAndHashCode(callSuper = false)
public class GoodsEvents {

    private String id;

    @Field(value = "insert_date")
    private String insertDate;

    @Field(value = "update_time")
    private Integer updateTime;

    private String price;

    private String stock;

}
