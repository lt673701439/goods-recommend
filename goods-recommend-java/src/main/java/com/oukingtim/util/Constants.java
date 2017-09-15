package com.oukingtim.util;

/**
 * Created by oukingtim
 */
public final class Constants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static class ES {
        public static final String INDEX = "elastic";
        public static final String TYPE_GOODS = "goods";
        public static final String TYPE_NOTES = "notes";
    }
    public static class Mongo {
        public static final String COLLECTION_BRANDS = "brands";
        public static final String COLLECTION_GOODS = "goods";
        public static final String COLLECTION_GOODS_EVENTS = "goods_events";
        public static final String COLLECTION_NOTES = "notes";
        public static final String COLLECTION_NOTES_EVENTS = "notes_events";
        public static final String COLLECTION_SELLERS = "sellers";
        public static final String COLLECTION_USERS = "users";
    }

}
