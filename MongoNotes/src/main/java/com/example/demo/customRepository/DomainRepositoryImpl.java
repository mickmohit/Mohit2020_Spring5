package com.example.demo.customRepository;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class DomainRepositoryImpl implements DomainRepositoryCustom {

	@Autowired
    MongoTemplate mongoTemplate;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public String updateName(String id, String key, String value) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
				
		//query.fields().include("userSettings").exclude("_id");;
		LOG.info(" query.....");
		
		User user = mongoTemplate.findOne(query, User.class);
		LOG.info(" user.....",user.getUserId());
		 
		/* working Java 8 code
		user.getUserSettings().forEach((k,v)->
		{
			if(key.equals(k)) 
			{user.getUserSettings().put(key, value);
			mongoTemplate.save(user);
			
			}
		});
		
		return "Update";*/
		
		
		if (user != null && (user.getUserSettings().containsKey(key))) {
			 {
			user.getUserSettings().put(key, value);
			mongoTemplate.save(user);
			}
			return "Key added.";
		} else {
			return "User not found.";
		}
				
	}

}
