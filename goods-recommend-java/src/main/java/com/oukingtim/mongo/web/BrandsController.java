package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.service.BrandsService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/brands")
public class BrandsController {

    @Autowired
    private BrandsService brandsService;

    @GetMapping(value = "/getAllBrandsList")
    public ResultVM getAllBrandsList(){
        List<Brands> list = brandsService.getAllBrandsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getBrandsById")
    public ResultVM getBrandsById(@RequestParam String id){
        Brands brands = brandsService.getBrandsById(id);
        return ResultVM.ok(brands);
    }

    @GetMapping(value = "/getBrandsByCondition")
    public ResultVM getBrandsByCondition(@RequestParam Map<String,Object> map){
        List<Brands> list = brandsService.getBrandsByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getBrandsCount")
    public ResultVM getBrandsCount(){
        Long count = brandsService.getBrandsCount();
        return ResultVM.ok(count);
    }
}
