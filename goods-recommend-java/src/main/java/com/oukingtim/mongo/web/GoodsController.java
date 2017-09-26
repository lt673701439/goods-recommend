package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.service.GoodsService;
import com.oukingtim.util.DateUtils;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/getForPageList")
    public ResultVM getForPageList(){
        List<Goods> list = goodsService.getForPageList(1,10,"");
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getByGoodsId")
    public ResultVM getByGoodsId(@RequestParam String goodsId){
        Goods goods = goodsService.getByGoodsId(goodsId);
        return ResultVM.ok(goods);
    }

    @GetMapping(value = "/getGoodsByCondition")
    public Map<String ,Object> getGoodsByCondition(@RequestParam int page,@RequestParam int limit,
                                                   @RequestParam String title,@RequestParam String country,
                                                   @RequestParam String sort){
        return goodsService.getGoodsByCondition(page,limit,title,country,sort);
    }

    @GetMapping(value = "/getGoodsCount")
    public ResultVM getGoodsCount(){
        Long count = goodsService.getGoodsCount("");
        return ResultVM.ok(count);
    }

    @GetMapping(value = "/getCountToday")
    public List<Map<String ,Object>> getCountToday(){
        String now = DateUtils.getDate();
        Long count = goodsService.getGoodsCount(now);
        Map<String,Object> map = new HashMap<>();
        map.put("name", 0);
        map.put("value", count);
        List<Map<String ,Object>> list = new ArrayList();
        list.add(map);
        return list;
    }

    @GetMapping(value = "/getGoodsByDate")
    public ResultVM getGoodsByDate(String startDate,String endDate){
        List<Goods> list = goodsService.getGoodsByDate(startDate,endDate,1,10,"");
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsByBrandId")
    public ResultVM getGoodsByBrandId(@RequestParam String brandId){
        List<Goods> list = goodsService.getGoodsByBrandId(brandId,1,10,"");
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsBySellerId")
    public ResultVM getGoodsBySellerId(@RequestParam String sellerId){
        List<Goods> list = goodsService.getGoodsBySellerId(sellerId,1,10,"");
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsByCategoryId")
    public ResultVM getGoodsByCategoryId(@RequestParam String categoryId){
        List<Goods> list = goodsService.getGoodsByCategoryId(categoryId,1,10,"");
        return ResultVM.ok(list);
    }

}
