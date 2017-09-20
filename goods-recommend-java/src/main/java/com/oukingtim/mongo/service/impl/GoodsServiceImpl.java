package com.oukingtim.mongo.service.impl;

import com.mongodb.*;
import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.repository.GoodsRepos;
import com.oukingtim.mongo.service.GoodsService;
import com.oukingtim.util.Constants;
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
    public List<Goods> getForPageList(int pageNumber, int pageSize, String sortType) {
        return super.getPageList(goodsRepos, pageNumber, pageSize, sortType);
    }

    @Override
    public Goods getByGoodsId(String goodsId) {
        return goodsRepos.getByGoodsId(goodsId);
    }

    @Override
    public List<Goods> getGoodsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_GOODS);
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
        }
        return list;
    }

    @Override
    public Long getGoodsCount(String date) {
        if (!"".equals(date)) {
            //查询大于等于传入时间
            DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_GOODS);
            BasicDBObject basicDBObject;
            basicDBObject = new BasicDBObject().append("insert_date",
                    new BasicDBObject().append(QueryOperators.GTE, date));
            DBCursor dbCursor = dbCollection.find(basicDBObject);
            return (long) dbCursor.count();
        } else {
            return goodsRepos.count();
        }

    }

    @Override
    public List<Goods> getGoodsByDate(String startDate,String endDate) {
        return super.getByDate(Constants.Mongo.COLLECTION_GOODS,startDate,endDate);
    }

    @Override
    public List<Goods> getGoodsByBrandId(String brandId) {
        return goodsRepos.getGoodsByBrandId(brandId);
    }

    @Override
    public List<Goods> getGoodsBySellerId(String sellerId) {
        return goodsRepos.getGoodsBySellerId(sellerId);
    }

    @Override
    public List<Goods> getGoodsByCategoryId(String categoryId) {
        return goodsRepos.getGoodsByCategoryId(categoryId);
    }

}
