package com.oukingtim.mongo.service;

import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BaseService {

    /**
     * 获取带排序的分页请求
     *
     * @param pageNumber 请求页数
     * @param pageSize   分页大小
     * @param sortType   排序方式，排序字段需要与VO中的field别名相同
     * @return 结果集
     */
    PageRequest getPageRequest(int pageNumber, int pageSize, String sortType);

    /**
     * 按时间段查询，时间条件可为空，同时为空时返回空数据
     * @param collectionName 集合名称
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param pageNumber 请求页数
     * @param pageSize   分页大小
     * @param sortType   排序方式，排序字段需要与VO中的field别名相同
     * @return 结果集
     */
    List getByDate(String collectionName,String startDate, String endDate,int pageNumber,int pageSize,String sortType);
}
