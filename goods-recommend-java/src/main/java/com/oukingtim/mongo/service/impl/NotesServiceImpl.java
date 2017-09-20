package com.oukingtim.mongo.service.impl;

import com.mongodb.*;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.repository.NotesRepos;
import com.oukingtim.mongo.service.NotesService;
import com.oukingtim.util.Constants;
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
    public List<Notes> getForPageList(int pageNumber, int pageSize, String sortType) {
        return super.getPageList(notesRepos,pageNumber,pageSize,sortType);
    }

    @Override
    public Notes getByNotesId(String notesId) {
        return notesRepos.getByNotesId(notesId);
    }

    @Override
    public List<Notes> getNotesByCondition(Map<String, Object> map) {
        DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_NOTES);
        BasicDBObject basicDBObject = new BasicDBObject();

        Pattern pattern = Pattern.compile("^.*" + map.get("title")
                + ".*$", Pattern.CASE_INSENSITIVE);//模糊查询
        basicDBObject.put("title", pattern);
        basicDBObject.put("item_type", map.get("itemType").toString());//等值查询
        DBCursor dbCursor = dbCollection.find(basicDBObject);

        List list = new ArrayList();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            list.add(dbObject);
        }
        return list;
    }

    @Override
    public Long getNotesCount(String date) {
        if (!"".equals(date)) {
            //查询大于等于传入时间
            DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_NOTES);
            BasicDBObject basicDBObject;
            basicDBObject = new BasicDBObject().append("insert_date",
                    new BasicDBObject().append(QueryOperators.GTE, date));
            DBCursor dbCursor = dbCollection.find(basicDBObject);
            return (long) dbCursor.count();
        } else {
            return notesRepos.count();
        }
    }

    @Override
    public List<Notes> getNotesByDate(String startDate, String endDate) {
        return super.getByDate(Constants.Mongo.COLLECTION_NOTES,startDate,endDate);
    }

}
