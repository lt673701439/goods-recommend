package com.oukingtim.mongo.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BaseService {

    /**
     * 处理分页请求
     *
     * @param repository repository的子类
     * @param pageNumber 请求页数，zero-based page index.
     * @param pageSize   分页大小
     * @param sortType   排序方式
     * @return
     */
    List getPageList(MongoRepository repository, int pageNumber, int pageSize, String sortType);
}
