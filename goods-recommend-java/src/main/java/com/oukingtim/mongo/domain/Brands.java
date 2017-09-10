package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Brands {

    private String id;

    private String ename;

    private String name;

    private String videoUrl;

    private String area;

    private String image;

    private String shortDesc;

    private String videoImageUrl;


}
