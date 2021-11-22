package com.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author skwang
 * @create 2021-03-08-14:55
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("b", 1);
        map.put("c", 2);//key必须不同，value可以相同
        map.put("e", 1);
        map.put("d", 4);
        System.out.println(map.get("b").equals(map.get("e")));
        System.out.println(map);
        map.remove("d");
        System.out.println(map);
        System.out.println(map.containsKey("b"));
        System.out.println(map.containsValue(2));
//        map.clear();
        System.out.println(map);

        map.values();//获取map集合里的value值
        Set<String> keys = map.keySet();//获取map集合里的key的集合

        //遍历map
        for(String key : keys) {
            System.out.println("key:" + key  + ".value:" + map.get(key));
        }

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for(Map.Entry<String, Integer> en : entries) {
            System.out.println("keys:" + en.getKey() + ",value:" + en.getValue());
        }
        //treemap根据KEY来排序  字典排序
    }
}
