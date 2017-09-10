package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.domain.NotesEvents;
import com.oukingtim.mongo.service.NotesEventsService;
import com.oukingtim.mongo.service.NotesService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/notesEvents")
public class NotesEventsController {

    @Autowired
    private NotesEventsService notesEventsService;

    @GetMapping(value = "/getAllNotesList")
    public ResultVM getAllNotesList(){
        List<NotesEvents> list = notesEventsService.getAllNotesEventsList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getNotesById")
    public ResultVM getNotesEventsById(@RequestParam String id){
        NotesEvents notesEvents = notesEventsService.getNotesEventsById(id);
        return ResultVM.ok(notesEvents);
    }

    @GetMapping(value = "/getNotesByCondition")
    public ResultVM getNotesByCondition(@RequestParam Map<String,Object> map){
        List<NotesEvents> list = notesEventsService.getNotesEventsByCondition(map);
        return ResultVM.ok(list);
    }
}
