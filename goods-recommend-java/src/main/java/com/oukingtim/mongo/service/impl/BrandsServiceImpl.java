package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.repository.BrandsRepos;
import com.oukingtim.mongo.service.BrandsService;
import com.oukingtim.util.Constants;
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
    public List<Brands> getForPageList(int pageNumber, int pageSize, String sortType) {
        return super.getPageList(brandsRepos,pageNumber,pageSize,sortType);
    }

    @Override
    public Brands getByBrandsId(String brandsId) {
        return brandsRepos.getByBrandsId(brandsId);
    }

    @Override
    public List<Brands> getBrandsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_BRANDS);
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
        }
        return list;
    }

    @Override
    public Long getBrandsCount() {
        return brandsRepos.count();
    }

}
