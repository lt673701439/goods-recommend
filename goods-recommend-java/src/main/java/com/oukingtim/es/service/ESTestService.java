package com.oukingtim.es.service;

import com.oukingtim.es.domain.ESTest;

import java.util.List;

public interface ESTestService {

    List getAll();

    ESTest getById(String id);

    List<ESTest> getByName(String name);

    void delete(ESTest esTest);

    void deleteAll();
}
