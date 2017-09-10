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
public class GoodsEvents {
    private String id;

    private String insertDate;

    private Integer updateTime;

    private String price;

    private String stock;

}
