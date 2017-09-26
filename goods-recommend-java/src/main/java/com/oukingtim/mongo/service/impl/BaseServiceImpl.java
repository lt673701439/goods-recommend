package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.QueryOperators;
import com.oukingtim.mongo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
class BaseServiceImpl implements BaseService {

    @Autowired
    MongoTemplate mongoTemplate;//MongoTemplate封装了很多方法

    @Override
    public PageRequest getPageRequest(int pageNumber, int pageSize, String sortType) {
        //处理排序
        Sort sort;
        if (sortType == null || "".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "insertDate");
        } else {
            sort = new Sort(Sort.Direction.DESC, sortType);
        }
        return new PageRequest(pageNumber-1, pageSize, sort);
    }

    @Override
    public List getByDate(String collectionName, String startDate, String endDate, int pageNumber, int pageSize, String sortType) {
        List list = new ArrayList();
        DBCollection dbCollection = mongoTemplate.getCollection(collectionName);
        //处理条件
        BasicDBObject basicDBObject = new BasicDBObject();
        if (startDate != null && !"".equals(startDate)) {
            basicDBObject.put(QueryOperators.GTE, startDate);
        }
        if (endDate != null && !"".equals(endDate)) {
            basicDBObject.put(QueryOperators.LTE, endDate);
        }
        BasicDBObject searchObj = new BasicDBObject();
        searchObj.put("insert_date", basicDBObject);
        //处理排序,字段要与VO的field对应
        BasicDBObject sort;
        if (sortType == null || "".equals(sortType)) {
            sort = new BasicDBObject("insertDate", -1);
        } else {
            sort = new BasicDBObject(sortType, -1);
        }
        Iterator iterator = dbCollection.find(searchObj).sort(sort).skip((pageNumber-1) * pageSize).limit(pageSize).iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
