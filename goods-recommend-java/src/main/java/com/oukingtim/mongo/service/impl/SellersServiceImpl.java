package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.MongoTest;
import com.oukingtim.mongo.domain.Sellers;
import com.oukingtim.mongo.repository.SellersRepos;
import com.oukingtim.mongo.service.SellersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class SellersServiceImpl extends BaseServiceImpl implements SellersService {

    @Autowired
    private SellersRepos sellersRepos;

    @Override
    public List<Sellers> getAllSellersList() {
        return sellersRepos.findAll();
    }

    @Override
    public Sellers getSellersById(String id) {
        return sellersRepos.findOne(id);
    }

    @Override
    public List<MongoTest> getSellersByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("sellers");
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("shopname")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("shopname", pattern);
        basicDBObject.put("sendFrom", map.get("sendFrom").toString());//等值查询
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