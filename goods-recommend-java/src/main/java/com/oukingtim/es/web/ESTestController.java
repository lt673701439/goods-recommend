package com.oukingtim.es.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oukingtim.es.domain.ESTest;
import com.oukingtim.es.service.ESTestService;
import com.oukingtim.mongo.domain.Notes;
import com.oukingtim.mongo.service.NotesService;
import com.oukingtim.web.vm.ResultVM;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/es")
public class ESTestController {

    @Autowired
    private ESTestService esTestService;

    @Autowired
    private NotesService importService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping(value = "/insert")
    public ResultVM insert(){
        boolean created = false;

        try {
            List<Notes> list = importService.getForPageList(0,100,"");
            for (Notes obj: list) {
                IndexResponse response = elasticsearchTemplate.getClient().prepareIndex("notes", "notes")
                        .setSource(new ObjectMapper().writeValueAsString(obj)
                        )
                        .get();
                created = response.isCreated();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVM.ok(created);
    }

    @GetMapping(value = "/searchESTest")
    public ResultVM searchESTest(String keyWord){
        List<ESTest> list = esTestService.searchESTest(keyWord);
        return ResultVM.ok(list);
    }
    @GetMapping(value = "/getAll")
    public ResultVM getAll(){
        List<ESTest> list = esTestService.getAll();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getById")
    public ResultVM getById(@RequestParam String id){
        ESTest esTest = esTestService.getById(id);
        System.out.println(esTest);
        return ResultVM.ok(esTest);
    }

    @GetMapping(value = "/getByName")
    public ResultVM getByName(@RequestParam String name){
        List<ESTest> list = esTestService.getByName(name);
        return ResultVM.ok(list);
    }

    /**
     * 按id删除
     * @param id 参数
     * @return 结果
     */
    @RequestMapping(value = "delete")
    public ResultVM delete(@RequestParam String id){
        esTestService.delete(id);
        return ResultVM.ok();
    }

    /**
     * 无效
     * @return 结果
     */
    @RequestMapping(value = "deleteAll")
    public ResultVM deleteAll(){
        esTestService.deleteAll();
        return ResultVM.ok();
    }

    /**
     * 按索引名称全部删除
     * @param indexName 索引名称
     * @return 结果
     */
    @RequestMapping(value = "deleteByIndexName")
    public ResultVM deleteByIndexName(String indexName){
        elasticsearchTemplate.deleteIndex(indexName);
        return ResultVM.ok();
    }
}
