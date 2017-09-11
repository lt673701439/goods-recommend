package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Sellers;
import com.oukingtim.mongo.service.SellersService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/sellers")
public class SellersController {

    @Autowired
    private SellersService sellersService;

    @GetMapping(value = "/getAllSellersList")
    public ResultVM getAllSellersList(){
        List<Sellers> list = sellersService.getAllSellersList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getSellersById")
    public ResultVM getSellersById(@RequestParam String id){
        Sellers sellers = sellersService.getSellersById(id);
        return ResultVM.ok(sellers);
    }

    @GetMapping(value = "/getSellersByCondition")
    public ResultVM getSellersByCondition(@RequestParam Map<String,Object> map){
        List<Sellers> list = sellersService.getSellersByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getSellersCount")
    public ResultVM getSellersCount(){
        Long count = sellersService.getSellersCount();
        return ResultVM.ok(count);
    }

}
