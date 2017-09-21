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
    private SellersService sellersService;

    @Autowired
    private UsersService usersService;

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
    public Map getGoodsList(int pageNumber, int pageSize) {
        List<Map> list = new ArrayList<>();

        List<Goods> goodsList = goodsService.getForPageList(pageNumber, pageSize,"insertDate");

        int startNum = (pageNumber - 1) * pageSize;

        for(Goods goods : goodsList) {
            startNum++;
            Map map = new HashMap();
            map.put("id", startNum);
            map.put("goodsid", goods.getGoodsId());
            map.put("updateTime", goods.getUpdateTime());
            map.put("name", goods.getName());
            map.put("country", goods.getCountry());

            map.put("image", goods.getImage());

            map.put("updateDate", goods.getUpdateDate());


            map.put("feature", goods.getFeature());
            map.put("syblings", goods.getSyblings().size() + 1);

            map.put("stock", goods.getStock());

            map.put("price", goods.getPrice());

            Double originalPrice = goods.getOriginalPrice() == null ? goods.getPrice() : goods.getOriginalPrice();
            map.put("originalPrice", originalPrice);

            String priceStatus = "平稳";
            if(goods.getPrice() > originalPrice) {
                priceStatus = "上涨";
            }else if(goods.getPrice() < originalPrice) {
                priceStatus = "下降";
            }
            map.put("priceStatus", priceStatus);


            map.put("brandName", brandService.getByBrandsId(goods.getBrandId()).getName());
            map.put("sellerName", sellersService.getBySellersId(goods.getSellerId()).getPublicName());

            map.put("desc", goods.getDesc());

            list.add(map);
        }

        Map resultMap = new HashMap();
        resultMap.put("items", list);
        resultMap.put("total", goodsService.getGoodsCount(""));
        return resultMap;
    }

    @Override
    public List<Map> getSyblings(String goodsId) {
        List<Map> list = new ArrayList<>();

        Goods goods = goodsService.getByGoodsId(goodsId);

        if(goods != null) {
            Map map = new HashMap();
            map.put("goodsid", goods.getGoodsId());
            map.put("name", goods.getName());
            list.add(map);

            for(Object obj : goods.getSyblings()) {
                Map objMap = (Map)obj;
                String syblingsId = (String)objMap.get("source_id");
                Goods sgoods = goodsService.getByGoodsId(syblingsId);
                if(sgoods != null) {
                    map = new HashMap();
                    map.put("goodsid", sgoods.getGoodsId());
                    map.put("name", sgoods.getName());
                    list.add(map);
                }
            }
        }

        return list;
    }

    @Override
    public Map getNotesList(int pageNumber, int pageSize) {
        List<Map> list = new ArrayList<>();

        List<Notes> notesList = notesService.getForPageList(pageNumber, pageSize,"insertDate");

        int startNum = (pageNumber - 1) * pageSize;

        for(Notes notes : notesList) {
            startNum++;
            Map map = new HashMap();
            map.put("id", startNum);
            map.put("notesid", notes.getNotesId());

            map.put("images", notes.getImages());
            map.put("title", notes.getTitle());
            map.put("likes", notes.getLikes());
            map.put("comments", notes.getComments());
            map.put("favCount", notes.getFavCount());
            map.put("time", notes.getTime());
            map.put("updateDate", notes.getUpdateDate());
            map.put("desc", notes.getDesc());
            map.put("userName", usersService.getByUsersId(notes.getUserId()).getNickname());

            list.add(map);
        }

        Map resultMap = new HashMap();
        resultMap.put("items", list);
        resultMap.put("total", notesService.getNotesCount(""));
        return resultMap;
    }

    @Override
    public Map test() {

        Map map = new HashMap();
        map.put("total", 1);
        List<Map> list = new ArrayList<>();
        Map cmap = new HashMap();
        cmap.put("id", 1);
        cmap.put("timestamp", "2017-09-27 10:10");
        cmap.put("author", "zhangsan");
        cmap.put("auditor", "lsi");
        cmap.put("title", "xxxx");
        cmap.put("forecast", "23.22");
        cmap.put("importance", 2);
        cmap.put("status", "published");
        cmap.put("display_time", "2017-09-27 10:10");
        cmap.put("pageviews", 400);

        list.add(cmap);
        map.put("items", list);

        return map;
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
