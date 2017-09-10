package com.oukingtim.mongo.web;

import com.oukingtim.mongo.domain.Users;
import com.oukingtim.mongo.service.UsersService;
import com.oukingtim.web.vm.ResultVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mongo/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/getAllUsersList")
    public ResultVM getAllUsersList(){
        List<Users> list = usersService.getAllUsersList();
        return ResultVM.ok(list);
    }

    @GetMapping(value = "/getUsersById")
    public ResultVM getUsersById(@RequestParam String id){
        Users users = usersService.getUsersById(id);
        return ResultVM.ok(users);
    }

    @GetMapping(value = "/getUsersByCondition")
    public ResultVM getUsersByCondition(@RequestParam Map<String,Object> map){
        List<Users> list = usersService.getUsersByCondition(map);
        return ResultVM.ok(list);
    }
}
