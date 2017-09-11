package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.Users;
import com.oukingtim.mongo.repository.UsersRepos;
import com.oukingtim.mongo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

    @Autowired
    private UsersRepos usersRepos;

    @Override
    public List<Users> getAllUsersList() {
        return usersRepos.findAll();
    }

    @Override
    public Users getUsersById(String id) {
        return usersRepos.findOne(id);
    }

    @Override
    public List<Users> getUsersByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("users");
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("nickname")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("nickname", pattern);
        basicDBObject.put("fans_total", Integer.parseInt(map.get("fansTotal").toString()));//等值查询
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
    public Long getUsersCount() {
        return usersRepos.count();
    }
}
