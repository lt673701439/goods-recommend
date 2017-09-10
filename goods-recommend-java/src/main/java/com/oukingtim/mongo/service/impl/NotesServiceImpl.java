package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.oukingtim.mongo.domain.MongoTest;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.repository.NotesRepos;
import com.oukingtim.mongo.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class NotesServiceImpl extends BaseServiceImpl implements NotesService {

    @Autowired
    private NotesRepos notesRepos;

    @Override
    public List<Notes> getAllNotesList() {
        return notesRepos.findAll();
    }

    @Override
    public Notes getNotesById(String id) {
        return notesRepos.findOne(id);
    }

    @Override
    public List<MongoTest> getNotesByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection("notes");
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("title")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("title", pattern);
        basicDBObject.put("itemType", map.get("itemType").toString());//等值查询
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
