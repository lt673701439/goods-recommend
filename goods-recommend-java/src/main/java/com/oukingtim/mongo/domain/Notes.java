package com.oukingtim.mongo.domain;

import com.oukingtim.util.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 *
 */
@Data
@Document(collection = Constants.Mongo.COLLECTION_NOTES)
@EqualsAndHashCode(callSuper = false)
public class Notes {

    @Field(value = "id")
    private String notesId;

    @Field(value = "event_type")
    private String eventType;

    private String imageb;

    private String images;

    private String timestamp;

    private Integer likes;

    @Field(value = "insert_date")
    private String insertDate;

    @Field(value = "update_date")
    private String updateDate;

    @Field(value = "note_type")
    private String noteType;

    @Field(value = "user_id")
    private String userId;

    @Field(value = "images_list")
    private List imagesList;

    private String title;

    private Integer comments;

    private String type;

    @Field(value = "fav_count")
    private Integer favCount;

    @Field(value = "response_url")
    private String responseUrl;

    private String host;

    private Integer level;

    private String desc;

    @Field(value = "events_count")
    private Integer eventsCount;

    @Field(value = "tags_info")
    private String tagsInfo;

    @Field(value = "item_type")
    private String itemType;

    private String time;

}
