package com.oukingtim.mongo.service.impl;

import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.service.*;
import com.oukingtim.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    @Override
    public List<Map> getGoodsList() {
        List<Map> list = new ArrayList<>();

        List<Goods> goodsList = goodsService.getForPageList(0,10,"insertDate");

        for(Goods goods : goodsList) {
            Map map = new HashMap();
            map.put("id", goods.getGoodsId());
            map.put("image", goods.getImage());

            Map cmap = new HashMap();
            cmap.put("name", goods.getName());
            String str = goods.getUpdateTime() + "000";
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cmap.put("updateTime", sf.format(new Date(Long.parseLong(str))));
            cmap.put("updateDate", goods.getUpdateDate());
            map.put("name", cmap);

            cmap = new HashMap();
            cmap.put("cname", goods.getCountry());
            cmap.put("ename", getCountryEname(goods.getCountry()));
            map.put("country", cmap);

            cmap = new HashMap();
            cmap.put("feature", goods.getFeature());
            cmap.put("syblings", goods.getSyblings().size() + 1);
            map.put("feature", cmap);

            map.put("stock", goods.getStock());

            map.put("price", goods.getPrice());

            Double originalPrice = goods.getOriginalPrice() == null ? goods.getPrice() : goods.getOriginalPrice();
            map.put("originalPrice", originalPrice);

            list.add(map);
        }
        return list;
    }

    private String getCountryEname(String cname) {
        Map<String, String> cnameMap = new HashMap<String, String>();
        cnameMap.put("中国", "China");
        cnameMap.put("美国", "USA");
        cnameMap.put("日本", "Japan");
        cnameMap.put("韩国", "Korea");
        cnameMap.put("意大利", "Italy");
        cnameMap.put("法国", "France");
        cnameMap.put("英国", "England");
        cnameMap.put("德国", "Germany");
        cnameMap.put("澳大利亚", "Australia");
        cnameMap.put("台湾", "Taiwan");
        cnameMap.put("丹麦", "Denmark");
        cnameMap.put("香港", "Hong Kong");
        cnameMap.put("新加坡", "Singapore");
        cnameMap.put("瑞士", "Switzerland");
        cnameMap.put("新西兰", "New Zealand");
        cnameMap.put("澳洲", "Australia");
        cnameMap.put("加拿大", "Canada");
        cnameMap.put("奥地利", "Austria");
        cnameMap.put("西班牙", "Spain");
        cnameMap.put("瑞典", "Sweden");
        cnameMap.put("泰国", "Thailand");
        cnameMap.put("巴西", "Brazil");
        cnameMap.put("荷兰", "Netherlands");
        cnameMap.put("希腊", "Greece");
        cnameMap.put("摩纳哥", "Monaco");
        cnameMap.put("比利时", "Belgium");
        cnameMap.put("芬兰", "Finland");
        cnameMap.put("以色列", "Israel");
        cnameMap.put("中国香港", "Hong Kong");
        cnameMap.put("爱尔兰", "Ireland");
        cnameMap.put("挪威", "Norway");

        if(cnameMap.containsKey(cname)) {
            return cnameMap.get(cname);
        }else {
            return cnameMap.get("中国");
        }
    }

}
