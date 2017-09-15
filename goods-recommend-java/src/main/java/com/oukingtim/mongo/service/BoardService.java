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
}
