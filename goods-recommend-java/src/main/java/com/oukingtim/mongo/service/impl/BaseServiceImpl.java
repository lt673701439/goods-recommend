package com.oukingtim.mongo.service.impl;

import com.mongodb.*;
import com.oukingtim.mongo.service.BaseService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
class BaseServiceImpl implements BaseService {

    @Autowired
    MongoTemplate mongoTemplate;//MongoTemplate封装了很多方法

    @Override
    public List getPageList(MongoRepository repository, int pageNumber, int pageSize, String sortType) {
        //处理排序
        Sort sort = null;
        if ("id".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "id");
        } else if ("insertDate".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "insert_date");
        }
        //处理查询
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, sort);
        Iterator iterator = repository.findAll(pageRequest).iterator();
        List list = new ArrayList();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public List getByDate(String collectionName,String startDate, String endDate) {
        List list = new ArrayList();
        DBCollection dbCollection = mongoTemplate.getCollection(collectionName);
        BasicDBObject basicDBObject = new BasicDBObject();
        if (!"".equals(startDate)) {
            basicDBObject.put(QueryOperators.GTE, startDate);
        }
        if (!"".equals(endDate)) {
            basicDBObject.put(QueryOperators.LTE, endDate);
        }
        BasicDBObject searchObj = new BasicDBObject();
        searchObj.put("insert_date",basicDBObject);
        DBCursor dbCursor = dbCollection.find(searchObj);
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            list.add(dbObject);
        }
        return list;
    }
}
