package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.domain.GoodsEvents;
import com.oukingtim.mongo.service.GoodsEventsService;
import com.oukingtim.mongo.service.GoodsService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/goodsEvents")
public class GoodsEventsController {

    @Autowired
    private GoodsEventsService goodsEventsService;

    @GetMapping(value = "/getAllGoodsEventsList")
    public ResultVM getAllGoodsList(){
        List<GoodsEvents> list = goodsEventsService.getAllGoodsEventsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsEventsById")
    public ResultVM getGoodsById(@RequestParam String id){
        GoodsEvents goodsEvents = goodsEventsService.getGoodsEventsById(id);
        return ResultVM.ok(goodsEvents);
    }

    @GetMapping(value = "/getGoodsEventsByCondition")
    public ResultVM getGoodsByCondition(@RequestParam Map<String,Object> map){
        List<GoodsEvents> list = goodsEventsService.getGoodsEventsByCondition(map);
        return ResultVM.ok(list);
    }
}
