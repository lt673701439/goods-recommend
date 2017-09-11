package com.oukingtim.mongo.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 */
@Data
@Document(collection = "notes_events")
@EqualsAndHashCode(callSuper = false)
public class NotesEvents {

    @Field(value = "insert_date")
    private String insertDate;

    private Integer comments;

    private Integer likes;

    private String id;

    @Field(value = "fav_count")
    private Integer favCount;

}
