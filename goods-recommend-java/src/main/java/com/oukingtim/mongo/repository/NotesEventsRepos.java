package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.NotesEvents;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesEventsRepos extends MongoRepository<NotesEvents, String> {

}
