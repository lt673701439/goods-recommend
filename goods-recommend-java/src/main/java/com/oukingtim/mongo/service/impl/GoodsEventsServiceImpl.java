package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.GoodsEvents;
import com.oukingtim.mongo.repository.GoodsEventsRepos;
import com.oukingtim.mongo.service.GoodsEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class GoodsEventsServiceImpl extends BaseServiceImpl implements GoodsEventsService {

    @Autowired
    private GoodsEventsRepos goodsEventsRepos;

    @Override
    public List<GoodsEvents> getAllGoodsEventsList() {
        return goodsEventsRepos.findAll();
    }

    @Override
    public GoodsEvents getGoodsEventsById(String id) {
        return goodsEventsRepos.findOne(id);
    }

    @Override
    public List<GoodsEvents> getGoodsEventsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("goods_events");
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("stock", Integer.parseInt(map.get("stock").toString()));//等值查询
        DBCursor dbCursor = dbCollection.find(basicDBObject);

        List list = new ArrayList();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            list.add(dbObject);
            System.out.println(dbObject);
        }
        return list;
    }

}
