package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.service.NotesService;
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
@RequestMapping(value = "/mongo/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @GetMapping(value = "/getForPageList")
    public ResultVM getForPageList(){
        List<Notes> list = notesService.getForPageList(0,10,"");
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getByNotesId")
    public ResultVM getByNotesId(@RequestParam String notesId){
        Notes notes = notesService.getByNotesId(notesId);
        return ResultVM.ok(notes);
    }

    @GetMapping(value = "/getNotesByCondition")
    public ResultVM getNotesByCondition(@RequestParam Map<String,Object> map){
        List<Notes> list = notesService.getNotesByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getNotesCount")
    public ResultVM getNotesCount(){
        Long count = notesService.getNotesCount("");
        return ResultVM.ok(count);
    }

    @GetMapping(value = "/getCountToday")
    public List<Map<String ,Object>> getCountToday(){
        String now = DateUtils.getDate();
        Long count = notesService.getNotesCount(now);
        Map<String,Object> map = new HashMap<>();
        map.put("name", 0);
        map.put("value", count);
        List<Map<String ,Object>> list = new ArrayList();
        list.add(map);
        return list;
    }

    @GetMapping(value = "/getNotesByDate")
    public ResultVM getNotesByDate(String startDate,String endDate){
        List<Notes> list = notesService.getNotesByDate(startDate,endDate,0,10,"");
        return ResultVM.ok(list);
    }

}
