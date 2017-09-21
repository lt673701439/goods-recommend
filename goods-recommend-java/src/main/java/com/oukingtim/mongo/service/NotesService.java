package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Notes;

import java.util.List;
import java.util.Map;

public interface NotesService {

    List<Notes> getForPageList(int pageNumber, int pageSize, String sortType);

    Notes getByNotesId(String notesId);

    List<Notes> getNotesByCondition(Map<String, Object> map);

    Long getNotesCount(String date);

    List<Notes> getNotesByDate(String startDate,String endDate,int pageNumber,int pageSize,String sortType);
}
