package com.example.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lizhong_o7 on 2019/8/4.
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    @Bean
    @Override
    public CacheManager cacheManager(){
        CompositeCacheManager compositeCacheManager=new CompositeCacheManager();
        CacheManager simpleCacheManager=simpleCacheManager();
        Set<CacheManager> cacheManagers=new HashSet<>();
        cacheManagers.add(simpleCacheManager);
        compositeCacheManager.setCacheManagers(cacheManagers);
        return compositeCacheManager;
    }
    @Bean
    public CacheManager simpleCacheManager(){
        return new ConcurrentMapCacheManager();
    }



}
