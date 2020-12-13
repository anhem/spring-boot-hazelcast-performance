package com.github.anhem.springboothazelcastperformance.controller;

import com.github.anhem.springboothazelcastperformance.controller.api.MessageDto;
import com.github.anhem.springboothazelcastperformance.model.CacheName;
import com.github.anhem.springboothazelcastperformance.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.github.anhem.springboothazelcastperformance.controller.api.MessageDto.MESSAGE_OK;

@RestController
@RequestMapping("cache")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("{cacheName}/get-cache")
    public Map<Object, Object> getCache(@PathVariable CacheName cacheName) {
        return cacheService.getCache(cacheName);
    }

    @GetMapping("{cacheName}/get-cache-by-keys")
    public Map<Object, Object> getCacheByKeys(@PathVariable CacheName cacheName) {
        return cacheService.getCacheByKeys(cacheName);
    }

    @GetMapping("{cacheName}/fill-cache")
    public MessageDto fillCache(@PathVariable CacheName cacheName) {
        cacheService.fillCache(cacheName);
        return MESSAGE_OK;
    }

    @GetMapping("{cacheName}/clear-cache")
    public MessageDto clearCache(@PathVariable CacheName cacheName) {
        cacheService.clearCache(cacheName);
        return MESSAGE_OK;
    }

}
