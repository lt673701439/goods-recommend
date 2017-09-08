package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.MongoTest;
import com.oukingtim.mongo.service.GetGoodsService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo")
public class GetGoodsController {

    @Autowired
    private GetGoodsService getGoodsService;

    @GetMapping(value = "/getAllGoodsList")
    public ResultVM getAllGoodsList(){
        List<MongoTest> list = getGoodsService.getAllGoodsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getGoodsById")
    public ResultVM getGoodsById(@RequestParam String id){
        MongoTest mongoTest = getGoodsService.getGoodsById(id);
        return ResultVM.ok(mongoTest);
    }

    @GetMapping(value = "/getGoodsByCondition")
    public ResultVM getGoodsByCondition(@RequestParam Map<String,Object> map){
        List<MongoTest> list = getGoodsService.getGoodsByCondition(map);
        return ResultVM.ok(list);
    }
}
