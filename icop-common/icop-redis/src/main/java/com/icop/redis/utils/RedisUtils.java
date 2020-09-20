package com.icop.redis.utils;

import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: liukj_mci
 * @date: 2019/9/10
 * @description：
 */
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private static CacheManager getMyRedisCacheManager(){
        return (CacheManager) BeanUtils.getBean("myRedisCacheManager");
    }

    private static RedisTemplate getmyRredisTemplate(){
        return (RedisTemplate) BeanUtils.getBean("myRedisCacheTemplate");
    }

    private RedisUtils() {super(); }

    /**
     * @Desc： 获取缓存内容中缓存key的值
     * @param cacheName  缓存名字
     * @param key  缓存KEY
     */
    public static <K extends Serializable,V extends Serializable> V getValue (String cacheName, K key){
        Cache cache = RedisUtils.getMyRedisCacheManager().getCache(cacheName);
        if(null != cache){
            Cache.ValueWrapper valueWrapper = cache.get(key);
            if(null != valueWrapper ){
                return (V) valueWrapper.get();
            }
        }
        return null;
    }

    /**
     * @Desc： 将value 依key的形式存储到cacheName的缓存中
     * @param cacheName  缓存名字
     * @param key  缓存KEY
     * @param value  缓存内容
     */
    public static <K extends Serializable,V extends Serializable> void put (String cacheName, K key, V value ){

        Cache cache = RedisUtils.getMyRedisCacheManager().getCache(cacheName);
        if(null != cache){
            cache.put(key,value);
        }else {
            throw new MyException(ExceptionCode.REDIS_NO_CACHE.getCode(),ExceptionCode.REDIS_NO_CACHE.getMsg(cacheName));
        }
    }

    /**
     * @Desc： 清空缓存cacheName下的所有缓存
     */
    public static void clear (String cacheName){
        Cache cache = RedisUtils.getMyRedisCacheManager().getCache(cacheName);
        if(null != cache){
            cache.clear();
        }else {
            throw new MyException(ExceptionCode.REDIS_NO_CACHE.getCode(),ExceptionCode.REDIS_NO_CACHE.getMsg(cacheName));
        }
    }

    /**
     * @Desc： 移除缓存cacheName中缓存KEY中的值
     */
    public static <K extends Serializable> void remove (String cacheName, K key){
        Cache cache = RedisUtils.getMyRedisCacheManager().getCache(cacheName);
        if(null != cache){
            cache.evict(key);
        }else {
            throw new MyException(ExceptionCode.REDIS_NO_CACHE.getCode(),ExceptionCode.REDIS_NO_CACHE.getMsg(cacheName));
        }
    }

    /**
     * @Desc： 获取所有的缓存key
     */
    public static List<String> getAllKeys (){
        Set keySet = RedisUtils.getmyRredisTemplate().keys("*");
        if(null != keySet){
            return (List<String>) keySet.stream().collect(Collectors.toList());
        }
        return null;
    }
}
