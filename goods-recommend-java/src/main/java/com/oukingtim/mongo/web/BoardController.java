package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Brands;
import com.oukingtim.mongo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/getGoodsAndNotesCount")
    public List<Map> getGoodsAndNotesCount() {
        List<Map> list = boardService.getGoodsAndNotesCount();
        return list;
    }

    @GetMapping(value = "/getAllEventsCount")
    public List<Map> getAllEventsCount() {
        List<Map> list = boardService.getAllEventsCount();
        return list;
    }

    @GetMapping(value = "/getGoodsTop")
    public List<Map> getGoodsTop() {
        return boardService.getGoodsTop();
    }

    @GetMapping(value = "/getGoodsImage")
    public List<Map> getGoodsImage() {
        return boardService.getGoodsImage();
    }

    @GetMapping(value = "/getGoodsCountryStat")
    public List<Map> getGoodsCountryStat() {
        return boardService.getGoodsCountryStat();
    }

    @GetMapping(value = "/getGoodsWeekCountStat")
    public List<Map> getGoodsWeekCountStat() {
        return boardService.getGoodsWeekCountStat();
    }

    @GetMapping(value = "/getNotesTop")
    public List<Map> getNotesTop() {
        return boardService.getNotesTop();
    }

    @GetMapping(value = "/getNotesImage")
    public List<Map> getNotesImage() {
        return boardService.getNotesImage();
    }

    @GetMapping(value = "/getBrandsList")
    public List<Map> getBrandsList() { return boardService.getBrandsList(); }

    @GetMapping(value = "/getGoodsList")
    public Map getGoodsList(@RequestParam int page, @RequestParam int limit) {
        return boardService.getGoodsList(page, limit); }

    @GetMapping(value = "/getSyblings")
    public List<Map> getSyblings(@RequestParam String goodsid) {
        return boardService.getSyblings(goodsid);
    }

    @GetMapping(value = "/getNotesList")
    public Map getNotesList(@RequestParam int page, @RequestParam int limit) {
        return boardService.getNotesList(page, limit); }

    @GetMapping(value = "/test")
    public Map test() { return boardService.test(); }
}
