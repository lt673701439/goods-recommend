package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.QueryOperators;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.repository.NotesRepos;
import com.oukingtim.mongo.service.NotesService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class NotesServiceImpl extends BaseServiceImpl implements NotesService {

    @Autowired
    private NotesRepos notesRepos;

    @Override
    public List<Notes> getForPageList(int pageNumber, int pageSize, String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        Iterator iterator = notesRepos.findAll(pageRequest).iterator();
        List<Notes> list = new ArrayList();
        while (iterator.hasNext()) {
            list.add((Notes) iterator.next());
        }
        return list;
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

        List<Notes> list = new ArrayList();
        while (dbCursor.hasNext()) {
            list.add((Notes) dbCursor.next());
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
    public List<Notes> getNotesByDate(String startDate, String endDate,int pageNumber,int pageSize,String sortType) {
        return super.getByDate(Constants.Mongo.COLLECTION_NOTES,startDate,endDate,pageNumber,pageSize,sortType);
    }

}
