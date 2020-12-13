package com.github.anhem.springboothazelcastperformance.config;

import com.hazelcast.config.Config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config config() {
        return new Config();
    }
}
