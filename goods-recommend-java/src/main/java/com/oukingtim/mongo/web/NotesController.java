package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Notes;
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
@RequestMapping(value = "/mongo/notes")
public class NotesController {

    @Autowired
    private NotesService NotesService;

    @GetMapping(value = "/getAllNotesList")
    public ResultVM getAllNotesList(){
        List<Notes> list = NotesService.getAllNotesList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getNotesById")
    public ResultVM getNotesById(@RequestParam String id){
        Notes notes = NotesService.getNotesById(id);
        return ResultVM.ok(notes);
    }

    @GetMapping(value = "/getNotesByCondition")
    public ResultVM getNotesByCondition(@RequestParam Map<String,Object> map){
        List<Notes> list = NotesService.getNotesByCondition(map);
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getNotesCount")
    public ResultVM getNotesCount(){
        Long count = NotesService.getNotesCount();
        return ResultVM.ok(count);
    }

}
