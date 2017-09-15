package com.oukingtim.mongo.service.impl;

import com.mongodb.*;
import com.oukingtim.mongo.domain.GoodsEvents;
import com.oukingtim.mongo.repository.GoodsEventsRepos;
import com.oukingtim.mongo.service.GoodsEventsService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodsEventsServiceImpl extends BaseServiceImpl implements GoodsEventsService {

    @Autowired
    private GoodsEventsRepos goodsEventsRepos;

    @Override
    public List<GoodsEvents> getAllGoodsEventsList() {
        return goodsEventsRepos.findAll();
    }

    @Override
    public GoodsEvents getByGoodsEventsId(String goodsEventsId) {
        return goodsEventsRepos.getByGoodsEventsId(goodsEventsId);
    }

    @Override
    public List<GoodsEvents> getGoodsEventsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_GOODS_EVENTS);
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("stock", Integer.parseInt(map.get("stock").toString()));//等值查询
        DBCursor dbCursor = dbCollection.find(basicDBObject);

        List list = new ArrayList();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            list.add(dbObject);
        }
        return list;
    }

    @Override
    public Long getGoodsEventsCount(String date) {
        if (!"".equals(date)) {
            //查询大于等于传入时间
            DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_GOODS_EVENTS);
            BasicDBObject basicDBObject;
            basicDBObject = new BasicDBObject().append("insert_date",
                    new BasicDBObject().append(QueryOperators.GTE, date));
            DBCursor dbCursor = dbCollection.find(basicDBObject);
            return (long) dbCursor.count();
        } else {
            return goodsEventsRepos.count();
        }
    }

}
