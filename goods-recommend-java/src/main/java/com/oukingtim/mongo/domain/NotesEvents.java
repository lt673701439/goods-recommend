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
@Document(collection = Constants.Mongo.COLLECTION_NOTES_EVENTS)
@EqualsAndHashCode(callSuper = false)
public class NotesEvents {

    @Field(value = "insert_date")
    private String insertDate;

    private Integer comments;

    private Integer likes;

    @Field(value = "id")
    private String notesEventsId;

    @Field(value = "fav_count")
    private Integer favCount;

}
