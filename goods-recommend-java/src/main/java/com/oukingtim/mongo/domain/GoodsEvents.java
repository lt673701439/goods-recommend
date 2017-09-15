package com.oukingtim.mongo.domain;

import com.oukingtim.util.Constants;
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
@Document(collection = Constants.Mongo.COLLECTION_GOODS_EVENTS)
@EqualsAndHashCode(callSuper = false)
public class GoodsEvents {

    @Field(value = "id")
    private String goodsEventsId;

    @Field(value = "insert_date")
    private String insertDate;

    @Field(value = "update_time")
    private Integer updateTime;

    private String price;

    private String stock;

}
