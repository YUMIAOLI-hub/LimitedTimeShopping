package com.miaoshaProject.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.miaoshaProject.service.CacheService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {
    private Cache<String,Object> commonCache =null;
    @PostConstruct
    public void init(){
        //设置缓存容器的初识容量，设置缓存中可以存储100个key，超过100个之后会按照LRU的策略移除缓存项
        //写缓存后多长时间过期
        commonCache = CacheBuilder.newBuilder().initialCapacity(10).maximumSize(100)
                      .expireAfterWrite(60,TimeUnit.SECONDS).build();
    }
    @Override
    public void setCommonCache(String key, Object value) {
        commonCache.put(key, value);
    }

    @Override
    public Object getFromCommonCache(String key) {
        return commonCache.getIfPresent(key);
    }
}
