package com.oukingtim.mongo.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.QueryOperators;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.repository.NotesRepos;
import com.oukingtim.mongo.service.NotesService;
import com.oukingtim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public Map<String, Object> getNotesByCondition(int pageNumber,int pageSize,String title,String noteType,String sortType) {
        PageRequest pageRequest = super.getPageRequest(pageNumber, pageSize, sortType);
        List list = new ArrayList();
        Long total;
        if("".equals(title) && "".equals(noteType)){
            list = this.getForPageList(pageNumber,pageSize,sortType);
            total = notesRepos.count();//总数
        } else if ("".equals(noteType)) {
            for (Object o : notesRepos.getByTitleLike(title, pageRequest)) {
                list.add(o);
            }
            total = notesRepos.countByTitleLike(title);//总数
        }else {
            for (Object o : notesRepos.getByTitleLikeAndNoteType(title, noteType, pageRequest)) {
                list.add(o);
            }
            total = notesRepos.countByTitleLikeAndNoteType(title,noteType);//总数
        }
        Map resultMap = new HashMap();

        resultMap.put("list",list);
        resultMap.put("total",total);
        return resultMap;
    }

    @Override
    public Long getNotesCount(String date) {
        if (!"".equals(date)) {
            //查询大于等于传入时间
            DBCollection dbCollection = mongoTemplate.getCollection(Constants.Mongo.COLLECTION_NOTES);
            BasicDBObject basicDBObject;
            basicDBObject = new BasicDBObject().append("insert_date",
                    new BasicDBObject().append(QueryOperators.GTE, date));
            return dbCollection.count(basicDBObject);
        } else {
            return notesRepos.count();
        }
    }

    @Override
    public List<Notes> getNotesByDate(String startDate, String endDate,int pageNumber,int pageSize,String sortType) {
        return super.getByDate(Constants.Mongo.COLLECTION_NOTES,startDate,endDate,pageNumber,pageSize,sortType);
    }

}
