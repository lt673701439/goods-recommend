package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.QueryOperators;
import com.oukingtim.mongo.domain.GoodsEvents;
import com.oukingtim.mongo.repository.GoodsEventsRepos;
import com.oukingtim.mongo.service.GoodsEventsService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator iterator = dbCollection.find(basicDBObject).iterator();

        List list = new ArrayList();
        while (iterator.hasNext()) {
            list.add(iterator.next());
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
            return dbCollection.count(basicDBObject);
        } else {
            return goodsEventsRepos.count();
        }
    }

}
