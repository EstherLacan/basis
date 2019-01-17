package com.jiangfw.common.lang.collection;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestMap {

    public static void main(String[] args) {
        // Map<Date, String> map = new HashMap<Date, String>();
        // map.put(new Date(1999, 2, 1), "123");
        // map.put(new Date(2999, 2, 1), "222");
        // map.put(new Date(3999, 2, 1), "333");
        // map.put(new Date(4999, 2, 1), "111");
        // System.out.println(map);

        Map<String, Integer> tmap = new TreeMap<String, Integer>();

        tmap.put("b1", 1999);
        tmap.put("b2", 2999);
        tmap.put("b1", 1199);
        tmap.put("b3", 3999);
        Integer v1 = tmap.get("b1");
        v1 += 1;
        Set<Map.Entry<String, Integer>> entry = tmap.entrySet();
        for (Map.Entry<String, Integer> entry2 : entry) {

        }
        System.out.println(v1 + "...." + tmap);
    }
}
