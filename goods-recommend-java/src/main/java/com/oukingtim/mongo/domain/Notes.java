package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Notes {

    private String eventType;

    private String imageb;

    private String timeStamp;

    private Integer likes;

    private String id;

    private String insertDate;

    private String updateDate;

    private String noteType;

    private String userId;

    private List imagesList;

    private String title;

    private Integer comments;

    private String type;

    private Integer favCount;

    private String responseUrl;

    private String host;

    private Integer level;

    private String desc;

    private Integer eventsCount;

    private String tagsInfo;

    private String itemType;

    private String time;

}
