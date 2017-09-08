package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.MongoTest;
import com.oukingtim.mongo.repository.MongoTestRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoTestController {

    @Autowired
    private MongoTestRepos mongoTestRepos;//继承了MongoRepository

    @Autowired
    private MongoTemplate mongoTemplate;//MongoTemplate封装了很多方法

    @RequestMapping("/save")
    public String save(){
        MongoTest mongoTest = new MongoTest();
        mongoTest.setName("王三");
        mongoTest.setAge(40);
        mongoTestRepos.save(mongoTest);

        mongoTest = new MongoTest();
        mongoTest.setName("赵四");
        mongoTest.setAge(50);
        mongoTestRepos.save(mongoTest);

        return "ok";
    }

    @RequestMapping("/find")
    public List<MongoTest> find(){
        return mongoTestRepos.findAll();
    }

    @RequestMapping("/findByName")
    public MongoTest findByName(String name){
        return mongoTestRepos.findByName(name);
    }

    @RequestMapping("/find2")
    public List<MongoTest> find2() {
        return mongoTemplate.findAll(MongoTest.class);
    }
}
