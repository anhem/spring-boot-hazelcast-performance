package com.github.anhem.springboothazelcastperformance.service;

import com.github.anhem.springboothazelcastperformance.model.CacheName;
import com.github.anhem.springboothazelcastperformance.model.java.Id;
import com.github.anhem.springboothazelcastperformance.model.java.Pojo1;
import com.github.anhem.springboothazelcastperformance.model.lombok.Lombok1;
import com.github.anhem.springboothazelcastperformance.model.lombok.LombokId;
import com.github.anhem.testpopulator.PopulateFactory;
import com.github.anhem.testpopulator.config.BuilderPattern;
import com.github.anhem.testpopulator.config.PopulateConfig;
import com.github.anhem.testpopulator.config.Strategy;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.anhem.springboothazelcastperformance.model.CacheName.JAVA_CACHE;
import static com.github.anhem.springboothazelcastperformance.model.CacheName.LOMBOK_CACHE;

@Slf4j
@Service
public class CacheService {

    private final HazelcastInstance hazelcastInstance;

    public CacheService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public Map<Object, Object> getCache(CacheName cacheName) {
        long t0 = System.currentTimeMillis();
        Map<Object, Object> cacheData = hazelcastInstance.getMap(cacheName.name());
        long t1 = System.currentTimeMillis();
        log.info("Got {} elements from {} in {}ms", cacheData.size(), cacheName, t1 - t0);
        return cacheData;
    }

    public Map<Object, Object> getCacheByKeys(CacheName cacheName) {
        Map<Object, Object> map = hazelcastInstance.getMap(cacheName.name());
        long t0 = System.currentTimeMillis();
        Set<Object> keys = map.keySet().stream().collect(Collectors.toSet());
        long t1 = System.currentTimeMillis();
        IMap<Object, Object> cacheData = hazelcastInstance.getMap(cacheName.name());
        long t2 = System.currentTimeMillis();
        log.info("Got {} elements from {} in {}ms (got keys in {} and data by keys in {})", cacheData.size(), cacheName, t2 - t0, t1 - t0, t2 - t1);
        return cacheData.getAll(keys);
    }

    public void fillCache(CacheName cacheName) {
        switch (cacheName) {
            case JAVA_CACHE:
                fillJavaCache();
                break;
            case LOMBOK_CACHE:
                fillLombokCache();
                break;
            default:
        }
    }

    public void clearCache(CacheName cacheName) {
        hazelcastInstance.getMap(cacheName.name()).clear();
    }

    private void fillJavaCache() {
        PopulateFactory populateFactory = new PopulateFactory();
        Map<Id, Pojo1> data = IntStream.range(0, 100000).mapToObj(i -> {
            Pojo1 pojo11 = populateFactory.populate(Pojo1.class);
            pojo11.setId(new Id(i));
            return pojo11;
        })
                .collect(Collectors.toMap(Pojo1::getId, pojo1 -> pojo1, (t1, t2) -> t1));
        addToCache(data, JAVA_CACHE);
    }

    private void fillLombokCache() {
        PopulateConfig populateConfig = PopulateConfig.builder()
                .strategyOrder(List.of(Strategy.BUILDER, Strategy.CONSTRUCTOR))
                .builderPattern(BuilderPattern.LOMBOK)
                .build();
        PopulateFactory populateFactory = new PopulateFactory(populateConfig);
        Map<LombokId, Lombok1> data = IntStream.range(0, 100000).mapToObj(i -> populateFactory.populate(Lombok1.class).toBuilder().lombokId(new LombokId(i)).build())
                .collect(Collectors.toMap(Lombok1::getLombokId, lombok1 -> lombok1, (t1, t2) -> t1));
        addToCache(data, LOMBOK_CACHE);
    }

    private <K, V> void addToCache(Map<K, V> data, CacheName cacheName) {
        long t0 = System.currentTimeMillis();
        hazelcastInstance.getMap(cacheName.name()).putAll(data);
        long t1 = System.currentTimeMillis();
        log.info("Added {} elements to {} in {}ms", data.size(), cacheName, t1 - t0);
    }
}
