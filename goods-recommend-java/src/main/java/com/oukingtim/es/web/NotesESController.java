package com.oukingtim.es.web;

import com.oukingtim.es.service.NotesESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/es/notes")
@RestController
public class NotesESController {

    @Autowired
    private NotesESService notesESService;

    @GetMapping(value = "/searchNotes")
    public List<HashMap> searchNotes(@RequestParam String keyWord) {
        return notesESService.searchNotes(keyWord);
    }
}
