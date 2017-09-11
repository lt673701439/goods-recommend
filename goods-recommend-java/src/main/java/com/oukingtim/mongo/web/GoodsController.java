package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.service.GoodsService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/getAllGoodsList")
    public ResultVM getAllGoodsList(){
        List<Goods> list = goodsService.getAllGoodsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsById")
    public ResultVM getGoodsById(@RequestParam String id){
        Goods goods = goodsService.getGoodsById(id);
        return ResultVM.ok(goods);
    }

    @GetMapping(value = "/getGoodsByCondition")
    public ResultVM getGoodsByCondition(@RequestParam Map<String,Object> map){
        List<Goods> list = goodsService.getGoodsByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsCount")
    public ResultVM getGoodsCount(){
        Long count = goodsService.getGoodsCount();
        return ResultVM.ok(count);
    }

    @GetMapping(value = "/getGoodsByBrandId")
    public ResultVM getGoodsByBrandId(@RequestParam String brandId){
        List<Goods> list = goodsService.getGoodsByBrandId(brandId);
        return ResultVM.ok(list);
    }
}
