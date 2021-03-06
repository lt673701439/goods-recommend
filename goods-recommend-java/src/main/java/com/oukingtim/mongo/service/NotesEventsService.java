package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.GoodsEvents;
import com.oukingtim.mongo.domain.NotesEvents;

import java.util.List;
import java.util.Map;

public interface NotesEventsService {

    List<NotesEvents> getAllNotesEventsList();

    NotesEvents getByNotesEventsId(String notesEventsId);

    List<NotesEvents> getNotesEventsByCondition(Map<String, Object> map);

    Long getNotesEventsCount(String date);
}
