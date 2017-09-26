package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.oukingtim.mongo.domain.Users;
import com.oukingtim.mongo.repository.UsersRepos;
import com.oukingtim.mongo.service.UsersService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public Users getByUsersId(String usersId) {
        return usersRepos.getByUsersId(usersId);
    }

    @Override
    public List<Users> getUsersByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_USERS);
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("nickname")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("nickname", pattern);
        basicDBObject.put("fans_total", Integer.parseInt(map.get("fansTotal").toString()));//等值查询
        Iterator iterator = dbCollection.find(basicDBObject).iterator();

        List list = new ArrayList();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public Long getUsersCount() {
        return usersRepos.count();
    }
}
