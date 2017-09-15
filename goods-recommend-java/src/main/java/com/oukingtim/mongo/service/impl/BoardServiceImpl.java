package com.oukingtim.mongo.service.impl;

import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.service.*;
import com.oukingtim.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private NotesService notesService;

    @Autowired
    private BrandsService brandService;

    @Autowired
    private GoodsEventsService goodsEventsService;

    @Autowired
    private NotesEventsService notesEventsService;

    @Override
    public List<Map> getGoodsAndNotesCount() {
        Long goodsCount = goodsService.getGoodsCount("");
        Long notesCount = notesService.getNotesCount("");
        Long count = goodsCount + notesCount;
        Map map = new HashMap<>();
        List<Map> list = new ArrayList();
        map.put("value", count);
        list.add(map);
        return list;
    }

    @Override
    public List<Map> getAllEventsCount() {
        Long goodsCount = goodsEventsService.getGoodsEventsCount("");
        Long notesCount = notesEventsService.getNotesEventsCount("");
        Long count = goodsCount + notesCount;
        Map map = new HashMap<>();
        List<Map> list = new ArrayList();
        map.put("value", count);
        list.add(map);
        return list;
    }

    @Override
    public List<Map> getGoodsTop() {
        List<Goods> goodsList = goodsService.getForPageList(0,5,"insertDate");
        List<Map> list = new ArrayList<>();
        for (int i = 0;i < goodsList.size();i++){
            Goods goods = goodsList.get(i);
            Map map = new HashMap();
            map.put("value",i);
            map.put("content",goods.getName());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map> getGoodsImage() {
        List<Goods> goodsList = goodsService.getForPageList(0,5,"insertDate");
        List<Map> list = new ArrayList<>();
        for (int i = 0;i < goodsList.size();i++){
            Goods goods = goodsList.get(i);
            Map map = new HashMap();
            map.put("url", goods.getImage());
            map.put("description", goods.getShortName());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map> getGoodsCountryStat() {
        List<Goods> goodsList = goodsService.getForPageList(0,1000,"insertDate");

        Map<String, Integer> countryCount = new HashMap<String, Integer>();
        for (Goods goods : goodsList) {
            if(countryCount.containsKey(goods.getCountry())) {
                countryCount.put(goods.getCountry(), countryCount.get(goods.getCountry()) + 1);
            }
            else {
                countryCount.put(goods.getCountry(), 1);
            }
        }

        List<Map> list = new ArrayList<>();
        for (String country : countryCount.keySet()) {
            Map map = new HashMap();
            map.put("value", countryCount.get(country));
            map.put("type", country);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map> getGoodsWeekCountStat() {

        List<Map> list = new ArrayList<>();

        int i = 7;
        String beginDate = DateUtils.getDateBefore(-1 * i);
        Long beginCount = goodsService.getGoodsCount(beginDate);
        while(i >= 1) {
            i--;
            String endDate = DateUtils.getDateBefore(-1 * i);
            Long endCount = goodsService.getGoodsCount(endDate);

            Map map = new HashMap();
            map.put("x", beginDate);
            map.put("y", beginCount - endCount);
            map.put("z", beginCount - endCount);
            list.add(map);

            beginDate = endDate;
            beginCount = endCount;
        }

        return list;
    }

    @Override
    public List<Map> getNotesTop() {
        List<Notes> notesList = notesService.getForPageList(0,5,"insertDate");
        List<Map> list = new ArrayList<>();
        for (int i = 0;i < notesList.size();i++){
            Notes notes = notesList.get(i);
            Map map = new HashMap();
            map.put("value",i);
            map.put("content",notes.getTitle());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map> getNotesImage() {
        List<Notes> notesList = notesService.getForPageList(0,5,"insertDate");
        List<Map> list = new ArrayList<>();
        for (int i = 0;i < notesList.size();i++){
            Notes notes = notesList.get(i);
            Map map = new HashMap();
            map.put("url", notes.getImages());
            map.put("description", notes.getTitle());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map> getBrandsList() {
        List<Brands> brandsList = brandService.getForPageList(1, 9, "");
        List<Map> list = new ArrayList<>();
        for (int i = 0;i < brandsList.size();i++){
            Brands brands = brandsList.get(i);
            Map map = new HashMap();
            map.put("content", brands.getName());
            map.put("type", "series" + i);
            list.add(map);
        }
        return list;
    }

}
