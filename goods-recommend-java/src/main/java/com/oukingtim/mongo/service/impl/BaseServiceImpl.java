package com.oukingtim.mongo.service.impl;

import com.mongodb.*;
import com.oukingtim.mongo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class BaseServiceImpl implements BaseService {

    @Autowired
    MongoTemplate mongoTemplate;//MongoTemplate封装了很多方法

    @Override
    public PageRequest getPageRequest(int pageNumber, int pageSize, String sortType) {
        //处理排序，排序字段需要与VO中的field别名相同，-1倒序，1正序
        Sort sort;
        if ("".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "insert_date");
        } else {
            sort = new Sort(Sort.Direction.DESC, sortType);
        }
        return new PageRequest(pageNumber, pageSize, sort);
    }

    @Override
    public List getByDate(String collectionName, String startDate, String endDate, int pageNumber, int pageSize, String sortType) {
        List list = new ArrayList();
        DBCollection dbCollection = mongoTemplate.getCollection(collectionName);
        //处理条件
        BasicDBObject basicDBObject = new BasicDBObject();
        if (!"".equals(startDate)) {
            basicDBObject.put(QueryOperators.GTE, startDate);
        }
        if (!"".equals(endDate)) {
            basicDBObject.put(QueryOperators.LTE, endDate);
        }
        BasicDBObject searchObj = new BasicDBObject();
        searchObj.put("insert_date", basicDBObject);
        //处理排序，排序字段需要与VO中的field别名相同，-1倒序，1正序
        BasicDBObject sort;
        if ("".equals(sortType)) {
            sort = new BasicDBObject("insert_date", -1);
        } else {
            sort = new BasicDBObject(sortType, -1);
        }
        DBCursor dbCursor = dbCollection.find(searchObj).sort(sort).skip((pageNumber) * pageSize).limit(pageSize);
        while (dbCursor.hasNext()) {
            list.add(dbCursor.next());
        }
        return list;
    }
}
