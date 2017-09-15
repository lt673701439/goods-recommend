package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.NotesEvents;
import com.oukingtim.mongo.service.NotesEventsService;
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
@RequestMapping(value = "/mongo/notesEvents")
public class NotesEventsController {

    @Autowired
    private NotesEventsService notesEventsService;

    @GetMapping(value = "/getAllNotesEventsList")
    public ResultVM getAllNotesEventsList(){
        List<NotesEvents> list = notesEventsService.getAllNotesEventsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getByNotesEventsId")
    public ResultVM getByNotesEventsId(@RequestParam String notesEventsId){
        NotesEvents notesEvents = notesEventsService.getByNotesEventsId(notesEventsId);
        return ResultVM.ok(notesEvents);
    }

    @GetMapping(value = "/getNotesEventsByCondition")
    public ResultVM getNotesEventsByCondition(@RequestParam Map<String,Object> map){
        List<NotesEvents> list = notesEventsService.getNotesEventsByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getNotesEventsCount")
    public ResultVM getNotesEventsCount(){
        Long count = notesEventsService.getNotesEventsCount("");
        return ResultVM.ok(count);
    }

    @GetMapping(value = "/getCountToday")
    public List<Map<String ,Object>> getCountToday(){
        String now = DateUtils.getDate();
        Long count = notesEventsService.getNotesEventsCount(now);
        Map<String ,Object> map = new HashMap<>();
        map.put("name", 0);
        map.put("value", count);
        List<Map<String ,Object>> list = new ArrayList();
        list.add(map);
        return list;
    }

}
