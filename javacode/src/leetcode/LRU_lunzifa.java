package leetcode;

import java.util.LinkedHashMap;

public class LRU_lunzifa {
    public static void main(String[] args) {

    }
}
class lru {
    //容量和一个哈希链表
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    /**
     * LinkedHashMap的几个API
     * containsKey(key)
     * put(key,val)加到链表的尾部
     * get(key)
     * remove(key)
     * KeySet()
     * @param cap
     */
    public lru(int cap) {
        this.cap = cap;
    }
    public int get (int key) {
        //首先判断哈希链表中存不存在这个key,存在就置为最近使用然后利用哈希链表自带的API改值
        if(!cache.containsKey(key)) {
            return -1;
        }
        //将key变成最近使用
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);//put默认放在链尾
        return val;
    }
    public void put(int key, int val) {
        //判断存在否，存在就修改直接put，不存在就看cap是不是等于size等于那就去掉链头节点再将新节点放到链尾
        if(cache.containsKey(key)) {
            cache.put(key, val);
            cache.remove(key);
            cache.put(key, val);
            return;
        }
        if(cache.size() == this.cap) {
            int firstkey = cache.keySet().iterator().next();
            cache.remove(firstkey);
        }
        cache.put(key,val);
    }
}