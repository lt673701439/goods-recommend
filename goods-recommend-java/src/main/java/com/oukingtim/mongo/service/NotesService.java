package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Goods;
import com.oukingtim.mongo.domain.Notes;

import java.util.List;
import java.util.Map;

public interface NotesService {

    List<Notes> getAllNotesList();

    Notes getNotesById(String id);

    List<Notes> getNotesByCondition(Map<String, Object> map);

    Long getNotesCount();
}
