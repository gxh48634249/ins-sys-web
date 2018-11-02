package com.ins.sys.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class Cache {


    @Bean
    public EhCacheManagerFactoryBean initFactory(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        CacheProperties cacheProperties = new CacheProperties();
        ehCacheManagerFactoryBean.setConfigLocation(cacheProperties.getEhcache().getConfig());
        return ehCacheManagerFactoryBean;
    }
//
//    @Bean("ehcachemanager")
//    @Primary
////    @Bean
//    public CacheManager init() {
//        return new CacheManager();
//    }
}
