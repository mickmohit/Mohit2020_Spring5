package com.example.demo.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.controller.TransactionController;


import javax.annotation.PreDestroy;

@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {

	
	
	  @Value("${spring.redis.host}") String hostName;
	  
	  @Value("${spring.redis.port}") Integer port;
	 
	 
	  private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	    
    
	/*
	 * @Autowired RedisConnectionFactory factory;// this was causing issue of double registration of bean jedisConnectionFactory 
	 */
	
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
		
		logger.info(hostName , port);
        RedisStandaloneConfiguration redisStandaloneConfiguration = 
        		new RedisStandaloneConfiguration(hostName, port);
       // redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));
        
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		
		  template.setConnectionFactory(jedisConnectionFactory());
		  template.setValueSerializer(new
		  GenericToStringSerializer<Object>(Object.class));
		  template.setEnableTransactionSupport(true);
      //return template;
        
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
	
	
	  
	 
}
