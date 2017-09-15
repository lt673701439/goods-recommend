package com.oukingtim.mongo.repository;

import com.oukingtim.mongo.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepos extends MongoRepository<Users, String> {

    Users getByUsersId(String usersId);
}
