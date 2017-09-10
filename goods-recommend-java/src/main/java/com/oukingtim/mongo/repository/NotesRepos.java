package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepos extends MongoRepository<Notes, String> {

}
