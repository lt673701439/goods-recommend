package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.QueryOperators;
import com.oukingtim.mongo.domain.NotesEvents;
import com.oukingtim.mongo.repository.NotesEventsRepos;
import com.oukingtim.mongo.service.NotesEventsService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class NotesEventsServiceImpl extends BaseServiceImpl implements NotesEventsService {

    @Autowired
    private NotesEventsRepos notesEventsRepos;

    @Override
    public List<NotesEvents> getAllNotesEventsList() {
        return notesEventsRepos.findAll();
    }

    @Override
    public NotesEvents getByNotesEventsId(String notesEventsId) {
        return notesEventsRepos.getByNotesEventsId(notesEventsId);
    }

    @Override
    public List<NotesEvents> getNotesEventsByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_NOTES_EVENTS);
        BasicDBObject basicDBObject = new BasicDBObject();

        basicDBObject.put("likes", Integer.parseInt(map.get("likes").toString()));//等值查询
        Iterator iterator = dbCollection.find(basicDBObject).iterator();

        List<NotesEvents> list = new ArrayList();
        while (iterator.hasNext()) {
            list.add((NotesEvents) iterator.next());
        }
        return list;
    }

    @Override
    public Long getNotesEventsCount(String date) {
        if (!"".equals(date)) {
            //查询大于等于传入时间
            DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_NOTES_EVENTS);
            BasicDBObject basicDBObject;
            basicDBObject = new BasicDBObject().append("insert_date",
                    new BasicDBObject().append(QueryOperators.GTE, date));
            return dbCollection.count(basicDBObject);
        } else {
            return notesEventsRepos.count();
        }
    }

}
