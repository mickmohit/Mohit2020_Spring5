package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.customRepository.DomainRepositoryCustom;
import com.example.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

	List<User> findByName(String name);

   
    //Supports native JSON query string
	 @Query(value="{ 'name' : ?0 }", fields="{ 'name' : 1, 'userSettings' : 1}")
	 List<User> findUserByName(String name);

    @Query("{name: { $regex: ?0 } })")
    List<User> findCustomUserByRegExName(String name);

}
