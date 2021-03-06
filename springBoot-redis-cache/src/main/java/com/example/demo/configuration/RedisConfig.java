package com.example.demo.configuration;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.entity.User;

@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {

	
	  @Value("${spring.redis.host}") String hostName;
	  
	  @Value("${spring.redis.port}") Integer port;
	 
    
    @Autowired
    RedisConnectionFactory factory;
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = 
        		new RedisStandaloneConfiguration(hostName, port);
       // redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));
        
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

	@Bean
	public RedisTemplate<String, User> redisTemplate() {
        final RedisTemplate<String, User> template = new RedisTemplate<String, User>();
		
		  template.setConnectionFactory(jedisConnectionFactory());
		  template.setValueSerializer(new
		  GenericToStringSerializer<Object>(Object.class));
		  template.setEnableTransactionSupport(true);
        
		/*
		 * RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		 * JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new
		 * JdkSerializationRedisSerializer();
		 * 
		 * template.setConnectionFactory(jedisConnectionFactory());
		 * 
		 * template.setKeySerializer(stringSerializer);
		 * template.setHashKeySerializer(stringSerializer);
		 * 
		 * template.setValueSerializer(jdkSerializationRedisSerializer);
		 * template.setHashValueSerializer(jdkSerializationRedisSerializer);
		 * 
		 * template.setEnableTransactionSupport(true); template.afterPropertiesSet();
		 */

        
        return template;	
	}
	
	 @PreDestroy
	    public void cleanRedis() {
	        factory.getConnection()
	                .flushDb();
	    }
}
