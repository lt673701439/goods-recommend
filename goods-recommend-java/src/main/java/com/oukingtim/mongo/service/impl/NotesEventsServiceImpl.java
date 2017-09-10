package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.NotesEvents;
import com.oukingtim.mongo.repository.NotesEventsRepos;
import com.oukingtim.mongo.service.NotesEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class NotesEventsServiceImpl extends BaseServiceImpl implements NotesEventsService {

    @Autowired
    private NotesEventsRepos notesEventsRepos;

    @Override
    public List<NotesEvents> getAllNotesEventsList() {
        return notesEventsRepos.findAll();
    }

    @Override
    public NotesEvents getNotesEventsById(String id) {
        return notesEventsRepos.findOne(id);
    }

    @Override
    public List<NotesEvents> getNotesEventsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("notes_events");
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("likes", Integer.parseInt(map.get("likes").toString()));//等值查询
        DBCursor dbCursor = dbCollection.find(basicDBObject);

        List list = new ArrayList();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            list.add(dbObject);
            System.out.println(dbObject);
        }
        return list;
    }

}
