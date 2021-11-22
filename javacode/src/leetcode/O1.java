package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class O1 {
    public static void main(String[] args) {

    }
}
//思想就是用一个hashmap保存idx
class RandomizedSet{
    ArrayList<Integer> list = new ArrayList();
    HashMap<Integer, Integer> map = new HashMap();
    boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }
    boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        //数组最后一个元素
        int lastElement = list.get(list.size() -1);
        //map的idx
        int idx = map.get(val);
        list.set(idx, lastElement);
        map.put(lastElement,idx);
        list.remove(list.size()-1);
        map.remove(val);
        return  true;
    }
    int getRandom() {
        Random rand = new Random();
        int idx = rand.nextInt(list.size());
        return list.get(idx);
    }

}