package com.oukingtim.es.service.impl;

import com.mongodb.DBObject;
import com.oukingtim.es.domain.ESTest;
import com.oukingtim.es.repository.ESTestRepos;
import com.oukingtim.es.service.ESTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("eSTestService")
public class ESTestServiceImpl implements ESTestService{

    @Autowired
    private ESTestRepos esTestRepos;

    @Override
    public List<ESTest> getAll() {
        List list = new ArrayList();
        for (Object esTest : esTestRepos.findAll()) {
            list.add(esTest);
            System.out.println(esTest);
        }
        return list;
    }

    @Override
    public ESTest getById(String id) {
        return esTestRepos.findOne(id);
    }

    @Override
    public List<ESTest> getByName(String name) {
        return esTestRepos.findByName(name);
    }

    @Override
    public void delete(ESTest esTest) {
        esTestRepos.delete(esTest);
    }

    @Override
    public void deleteAll() {
        esTestRepos.deleteAll();
    }
}
