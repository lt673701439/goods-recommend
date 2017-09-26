package com.oukingtim.mongo.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<Map> getGoodsAndNotesCount();

    List<Map> getAllEventsCount();

    List<Map> getGoodsTop();

    List<Map> getGoodsImage();

    List<Map> getGoodsCountryStat();

    List<Map> getGoodsWeekCountStat();

    List<Map> getNotesTop();

    List<Map> getNotesImage();

    List<Map> getBrandsList();

    Map getGoodsList(int pageNumber, int pageSize,String title,String country,String sortType);

    List<Map> getSyblings(String goodsId);

    Map getNotesList(int pageNumber, int pageSize,String title,String noteType,String sortType);

    Map test();
}
