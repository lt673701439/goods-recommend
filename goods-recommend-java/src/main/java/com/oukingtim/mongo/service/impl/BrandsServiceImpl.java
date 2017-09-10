package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.domain.MongoTest;
import com.oukingtim.mongo.repository.BrandsRepos;
import com.oukingtim.mongo.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class BrandsServiceImpl extends BaseServiceImpl implements BrandsService {

    @Autowired
    private BrandsRepos brandsRepos;

    @Override
    public List<Brands> getAllBrandsList() {
        return brandsRepos.findAll();
    }

    @Override
    public Brands getBrandsById(String id) {
        return brandsRepos.findOne(id);
    }

    @Override
    public List<MongoTest> getBrandsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("brands");
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("name")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("name", pattern);
        basicDBObject.put("area", map.get("area").toString());//等值查询
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
