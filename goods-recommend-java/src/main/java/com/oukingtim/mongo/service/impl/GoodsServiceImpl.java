package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.QueryOperators;
import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.repository.GoodsRepos;
import com.oukingtim.mongo.service.GoodsService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepos goodsRepos;

    @Override
    public List<Goods> getForPageList(int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        Iterator iterator = goodsRepos.findAll(pageRequest).iterator();
        List<Goods> list = new ArrayList();
        while (iterator.hasNext()) {
            list.add((Goods) iterator.next());
        }
        return list;
    }

    @Override
    public Goods getByGoodsId(String goodsId) {
        return goodsRepos.getByGoodsId(goodsId);
    }

    @Override
    public Map<String, Object> getGoodsByCondition(int pageNumber, int pageSize, String title,
                                                   String country, String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        List list = new ArrayList();
        Long total;
        if ("".equals(title) && "".equals(country)) {
            list = this.getForPageList(pageNumber, pageSize, sortType);
            total = goodsRepos.count();//总数
        } else if ("".equals(country)) {
            for (Object o : goodsRepos.getByNameLike(title, pageRequest)) {
                list.add(o);
            }
            total = goodsRepos.countByNameLike(title);//总数
        } else {
            for (Object o : goodsRepos.getByNameLikeAndCountry(title, country, pageRequest)) {
                list.add(o);
            }
            total = goodsRepos.countByNameLikeAndCountry(title, country);//总数
        }
        Map resultMap = new HashMap();

        resultMap.put("list", list);
        resultMap.put("total", total);
        return resultMap;
    }

    @Override
    public Long getGoodsCount(String date) {
        if (!"".equals(date)) {
            //查询大于等于传入时间
            DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_GOODS);
            BasicDBObject basicDBObject;
            basicDBObject = new BasicDBObject().append("insert_date",
                    new BasicDBObject().append(QueryOperators.GTE, date));
            return dbCollection.count(basicDBObject);
        } else {
            return goodsRepos.count();
        }

    }

    @Override
    public List<Goods> getGoodsByDate(String startDate, String endDate, int pageNumber, int pageSize, String sortType) {
        return super.getByDate(Constants.Mongo.COLLECTION_GOODS, startDate, endDate, pageNumber, pageSize, sortType);
    }

    @Override
    public List<Goods> getGoodsByBrandId(String brandId, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        Iterator iterator = goodsRepos.getGoodsByBrandId(brandId, pageRequest).iterator();
        List<Goods> list = new ArrayList();
        while (iterator.hasNext()) {
            list.add((Goods) iterator.next());
        }
        return list;
    }

    @Override
    public List<Goods> getGoodsBySellerId(String sellerId, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        Iterator iterator = goodsRepos.getGoodsBySellerId(sellerId, pageRequest).iterator();
        List<Goods> list = new ArrayList();
        while (iterator.hasNext()) {
            list.add((Goods) iterator.next());
        }
        return list;
    }

    @Override
    public List<Goods> getGoodsByCategoryId(String categoryId, int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        Iterator iterator = goodsRepos.getGoodsByCategoryId(categoryId, pageRequest).iterator();
        List<Goods> list = new ArrayList();
        while (iterator.hasNext()) {
            list.add((Goods) iterator.next());
        }
        return list;
    }

}
