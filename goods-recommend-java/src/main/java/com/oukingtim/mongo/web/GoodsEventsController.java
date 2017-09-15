package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.GoodsEvents;
import com.oukingtim.mongo.service.GoodsEventsService;
import com.oukingtim.util.DateUtils;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/mongo/goodsEvents")
public class GoodsEventsController {

    @Autowired
    private GoodsEventsService goodsEventsService;

    @GetMapping(value = "/getAllGoodsEventsList")
    public ResultVM getAllGoodsEventsList(){
        List<GoodsEvents> list = goodsEventsService.getAllGoodsEventsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getByGoodsEventsId")
    public ResultVM getByGoodsEventsId(@RequestParam String goodsEventsId){
        GoodsEvents goodsEvents = goodsEventsService.getByGoodsEventsId(goodsEventsId);
        return ResultVM.ok(goodsEvents);
    }

    @GetMapping(value = "/getGoodsEventsByCondition")
    public ResultVM getGoodsEventsByCondition(@RequestParam Map<String,Object> map){
        List<GoodsEvents> list = goodsEventsService.getGoodsEventsByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsEventsCount")
    public ResultVM getGoodsEventsCount(){
        Long count = goodsEventsService.getGoodsEventsCount("");
        return ResultVM.ok(count);
    }

    @GetMapping(value = "/getCountToday")
    public List<Map<String ,Object>> getCountToday(){
        String now = DateUtils.getDate();
        Long count = goodsEventsService.getGoodsEventsCount(now);
        Map<String ,Object> map = new HashMap<>();
        map.put("name", 0);
        map.put("value", count);
        List<Map<String ,Object>> list = new ArrayList();
        list.add(map);
        return list;
    }
}
