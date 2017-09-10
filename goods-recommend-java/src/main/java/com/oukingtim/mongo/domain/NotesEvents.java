package com.oukingtim.mongo.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NotesEvents {

    private String insertDate;

    private Integer comments;

    private Integer likes;

    private String id;

    private Integer favCount;

}
