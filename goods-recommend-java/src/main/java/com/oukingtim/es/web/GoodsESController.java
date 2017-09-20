package com.oukingtim.es.web;

import com.oukingtim.es.service.GoodsESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/es/goods")
@RestController
public class GoodsESController {

    @Autowired
    private GoodsESService goodsESService;

    @GetMapping(value = "/searchGoods")
    public List<HashMap> searchGoods(@RequestParam String keyWord) {
        return goodsESService.searchGoods(keyWord);
    }
}
