package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepos extends MongoRepository<Notes, String> {

    Notes getByNotesId(String notesID);

    Page<Notes> getByTitleLikeAndNoteType(String name, String noteType, Pageable pageable);

    Long countByTitleLikeAndNoteType(String name,String noteType);

    Page<Notes> getByTitleLike(String title, Pageable pageable);

    Long countByTitleLike(String title);
}
