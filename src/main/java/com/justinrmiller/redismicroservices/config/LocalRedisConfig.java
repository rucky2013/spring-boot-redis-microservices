package com.justinrmiller.redismicroservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Justin Miller (Copyright 2015)
 */
@Configuration
@ComponentScan(basePackages="com.justinrmiller.redismicroservices.service")
public class LocalRedisConfig {
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(){
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        return redisTemplate;
    }
}