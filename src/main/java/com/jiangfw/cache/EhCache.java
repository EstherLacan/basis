package com.jiangfw.cache;

import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCache {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(1);
        // CacheManager manager = new CacheManager();

        CacheManager singletonManager = CacheManager.create();
        Cache memoryOnlyCache = new Cache("testCache", 5000, false, false, 5, 2);
        singletonManager.addCache(memoryOnlyCache);
        Cache cache = singletonManager.getCache("testCache");

        Element element = new Element("key1", "value1");
        cache.put(element);
        cache.put(new Element("key1", "value2"));

        element = cache.get("key1");
        Serializable value = element.getValue();
        System.out.println(value);

        int elementsInMemory = cache.getSize();
        System.out.println(elementsInMemory);

        long elementsInMemory2 = cache.getMemoryStoreSize();
        System.out.println(elementsInMemory2);

        Object obj = element.getObjectValue();
        cache.remove("key1");
        System.out.println(obj);
        singletonManager.shutdown();
        // manager.shutdown();

        System.out.println(2);
    }

}
