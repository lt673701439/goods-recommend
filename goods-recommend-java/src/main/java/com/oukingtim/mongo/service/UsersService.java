package com.oukingtim.mongo.service;

import com.oukingtim.mongo.domain.Users;

import java.util.List;
import java.util.Map;

public interface UsersService {

    List<Users> getAllUsersList();

    Users getByUsersId(String usersId);

    List<Users> getUsersByCondition(Map<String, Object> map);

    Long getUsersCount();
}
