package com.oukingtim.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Sellers {

    private String logistics;

    private String sellerId;

    private String shopname;

    private String videoUrl;

    private String image;

    private String sendFrom;

    private String publicName;

    private String role;

    private Boolean priceGuarantee;

    private String follow;

    private String id;

    private String videoImageUrl;

    private String dportId;

}
