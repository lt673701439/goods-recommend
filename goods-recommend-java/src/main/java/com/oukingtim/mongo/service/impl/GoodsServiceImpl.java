package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.repository.GoodsRepos;
import com.oukingtim.mongo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepos goodsRepos;

    @Override
    public List<Goods> getAllGoodsList() {
        return goodsRepos.findAll();
    }

    @Override
    public Goods getGoodsById(String id) {
        return goodsRepos.findOne(id);
    }

    @Override
    public List<Goods> getGoodsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("goods");
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("feature")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("feature", pattern);
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

    @Override
    public Long getGoodsCount() {
        return goodsRepos.count();
    }

    @Override
    public List<Goods> getGoodsByBrandId(String brandId) {
        return goodsRepos.getGoodsByBrandId(brandId);
    }

}
