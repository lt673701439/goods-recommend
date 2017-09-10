package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Users {

    private String image;

    private Integer discoverysTotal;

    private String nickname;

    private String id;

    private Integer fansTotal;

}
