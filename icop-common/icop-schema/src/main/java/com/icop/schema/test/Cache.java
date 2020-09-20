package com.icop.schema.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/7/14
 * @description：
 */

public class Cache implements org.apache.ibatis.cache.Cache {
    Map map = new HashMap();
    private String id;
    public Cache(String id){
        this.id=id;
    }
    @Override
    public String getId() {
        System.out.println("com.icop.schema.test.Cache::getId "+id);
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        System.out.println("com.icop.schema.test.Cache::putObject "+o);
        map.put(o,o1);
    }

    @Override
    public Object getObject(Object o) {
        System.out.println("com.icop.schema.test.Cache::getObject "+o);
        return map.get(o);
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
