package com.oukingtim.mongo.service.impl;

import com.oukingtim.mongo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    public MongoTemplate mongoTemplate;//MongoTemplate封装了很多方法

}
