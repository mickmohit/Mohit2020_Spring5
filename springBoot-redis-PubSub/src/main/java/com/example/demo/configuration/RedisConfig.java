package com.example.demo.configuration;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.example.demo.consumer.CustomerInfoSubscriber;
import com.example.demo.entity.Customer;
import com.example.demo.publisher.CustomerInfoPublisher;
import com.example.demo.publisher.CustomerInfoPublisherImpl;



@Configuration
public class RedisConfig {

	 @Value("${spring.redis.host}") 
	 String hostName;
	  
	  @Value("${spring.redis.port}") 
	  Integer port;

	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = 
        		new RedisStandaloneConfiguration(hostName, port);
       // redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));
        
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

	@Bean
	public RedisTemplate<String, Customer> redisTemplate() {
        final RedisTemplate<String, Customer> template = new RedisTemplate<String, Customer>();
		
		  template.setConnectionFactory(jedisConnectionFactory());
		  template.setValueSerializer(new
		  GenericToStringSerializer<Object>(Object.class));
		  template.setEnableTransactionSupport(true);
        return template;
	}
	
	@Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new CustomerInfoSubscriber());
    }
	
	@Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        container.setTaskExecutor(Executors.newFixedThreadPool(4));
        return container;
    }

    @Bean
    CustomerInfoPublisher redisPublisher() {
        return new CustomerInfoPublisherImpl(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}
