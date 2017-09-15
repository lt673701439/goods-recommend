package com.oukingtim.es.service;

import com.oukingtim.mongo.domain.Notes;

import java.util.HashMap;
import java.util.List;

public interface NotesESService {

    List<HashMap> searchNotes(String keyWord);
}
