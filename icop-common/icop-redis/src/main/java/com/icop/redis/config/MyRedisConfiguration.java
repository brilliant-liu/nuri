package com.icop.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/6/18
 * @description：
 */

@Configuration("myRedisConf")
@Slf4j
public class MyRedisConfiguration {

    private static final String defaultSerializationType="0";
    private Map<String, RedisCacheConfiguration> myRedisConfMap =new HashMap<>();

    /**
     * @Desc： 策略初始化
     */
    @PostConstruct
    private void initStrategy() {

        log.info("当前缓存存储内容模式(1为Json格式,0为默认以对象存储格式)："+RedisCacheConf.serializationType);

        for(RedisCacheConf redisCacheConf : RedisCacheConf.values()){
            RedisCacheConfiguration redisCacheConfiguration;
            if(defaultSerializationType.equals(RedisCacheConf.serializationType)){
                //key默认：StringRedisSerializer 值序列化器 ，内容默认为：JdkSerializationRedisSerializer
                redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues().entryTtl(Duration.ofSeconds(Integer.parseInt(redisCacheConf.entryTtl)));
            }else{
                log.info("当前缓存存储内容模式为JSON格式！");
                Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
                ObjectMapper om = new ObjectMapper();
                om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
                om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
                om.setDateFormat(DateFormat.getDateTimeInstance());
                jackson2JsonRedisSerializer.setObjectMapper(om);
                redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                        .entryTtl(Duration.ofSeconds(Integer.parseInt(redisCacheConf.entryTtl)));
            }
            myRedisConfMap.put(redisCacheConf.cacheName,redisCacheConfiguration);
            log.info("当前redia缓存内容：时间=" + redisCacheConf.cacheName + ":" + redisCacheConf.entryTtl);
        }
    }

    /**
     * @Desc： 自定义redis缓存管理器
     */
    @Bean("myRedisCacheManager")
    public CacheManager configRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        CacheManager redisCacheManager;
        redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).withInitialCacheConfigurations(this.myRedisConfMap).build();
        return redisCacheManager;
    }

    /**
     * @Desc： 设置RedisTemplate模板的key的序列化
     */
    @Bean("myRedisCacheTemplate")
    public RedisTemplate<Object, Object> configRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        StringRedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        return redisTemplate;
    }
}
