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
     * @return 结果集
     */
    List getPageList(MongoRepository repository, int pageNumber, int pageSize, String sortType);

    /**
     * 按时间段查询，时间条件可为空，同时为空时返回空数据
     * @param collectionName 集合名称
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 结果集
     */
    List getByDate(String collectionName,String startDate, String endDate);
}
