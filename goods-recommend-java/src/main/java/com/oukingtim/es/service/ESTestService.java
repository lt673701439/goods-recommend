package com.oukingtim.es.service;

import com.oukingtim.es.domain.ESTest;

import java.util.List;

public interface ESTestService {

    List<ESTest> getAll();

    List<ESTest> searchESTest(String keyWord);

    ESTest getById(String id);

    List<ESTest> getByName(String name);

    void delete(String id);

    void deleteAll();
}
