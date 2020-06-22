package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

//import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.config.JCacheConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;

@Configuration
@EnableCaching
public class JCacheConfig {

	/*public JCacheManagerCustomizer cacheCustomizer()
	{
		return new JCacheManagerCustomizer() {
			
			@Override
			public void customize(CacheManager cacheManager) {
				
			MutableConfiguration<Object, Object> config= new MutableConfiguration<Object, Object>();
			config.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.TEN_MINUTES));
			config.setStatisticsEnabled(true);
			cacheManager.createCache("userCache", config);
			cacheManager.createCache("addressCache", config);
			}
		};
	}*/
	
	@Bean //it is more generic it is interface implementation and it can be coupled with other cache , above one is only limited to JCache
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		List<Cache> cacheList = new ArrayList<Cache>();
		cacheList.add(new ConcurrentMapCache("userCache"));
		cacheList.add(new ConcurrentMapCache("addressCache"));
		cacheManager.setCaches(cacheList);
		return cacheManager;
	}
}
