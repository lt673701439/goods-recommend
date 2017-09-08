package com.oukingtim.es.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oukingtim.es.domain.ESTest;
import com.oukingtim.es.service.ESTestService;
import com.oukingtim.web.vm.ResultVM;
import org.apache.ibatis.annotations.Delete;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
@RequestMapping(value = "/es")
public class ESTestController {

    @Autowired
    private ESTestService esTestService;

    @RequestMapping(value = "/insert")
    public ResultVM insert(@RequestParam Map<String,Object> map){
        boolean created = false;
        try {
            ESTest esTest = new ESTest();
            esTest.setId(map.get("id").toString());
            esTest.setName(map.get("name").toString());
            esTest.setAge(map.get("age").toString());
            //Add transport addresses and do something with the client...
            Settings settings = Settings.settingsBuilder()
                    .put("cluster.name", "elasticsearch").build();
            // on startup
            TransportClient client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

            IndexResponse response = client.prepareIndex("elastic", "estest", esTest.getId())
                    .setSource(new ObjectMapper().writeValueAsString(esTest)
                    )
                    .get();
            created = response.isCreated();
            client.close();// on shutdown
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultVM.ok(created);
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
     * 未调通
     * @param esTest
     * @return
     */
    @DeleteMapping(value = "delete")
    public ResultVM delete(@RequestParam ESTest esTest){
        esTestService.delete(esTest);
        return ResultVM.ok();
    }

    /**
     * 未调通
     * @param esTest
     * @return
     */
    @DeleteMapping(value = "deleteAll")
    public ResultVM deleteAll(@RequestParam ESTest esTest){
        esTestService.deleteAll();
        return ResultVM.ok();
    }
}
