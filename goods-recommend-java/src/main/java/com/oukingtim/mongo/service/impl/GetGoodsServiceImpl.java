package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.MongoTest;
import com.oukingtim.mongo.repository.GetGoodsRepos;
import com.oukingtim.mongo.service.GetGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class GetGoodsServiceImpl implements GetGoodsService {

    @Autowired
    private GetGoodsRepos getGoodsRepos;//继承了MongoRepository

    @Autowired
    private MongoTemplate mongoTemplate;//MongoTemplate封装了很多方法

    @Override
    public List<MongoTest> getAllGoodsList() {
        return getGoodsRepos.findAll();
    }

    @Override
    public MongoTest getGoodsById(String id) {
        return getGoodsRepos.findOne(id);
    }

    @Override
    public List<MongoTest> getGoodsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("mongoTest");
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("name")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
//        basicDBObject.put("name", "张三");
        basicDBObject.put("name", pattern);
        basicDBObject.put("age", Integer.parseInt(map.get("age").toString()));//等值查询
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
