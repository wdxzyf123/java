package leetcode;

import java.util.HashMap;

public class LRU {
    public static void main(String[] args) {
        LRUCache l = new LRUCache(3);
        l.put(1,3);
        l.put(2,33);
        l.put(3,333);
        l.put(4,3333);

        Node PR = l.cache.head.next;
        while(PR.next != null) {
            System.out.println(PR.val);
            PR = PR.next;
        }
        System.out.println(l.get(3));

    }
}
/**
 * LUR算法清除缓存
 * 最近最少使用，最核心的数据结构就是哈希链表LinkedHashMap，越靠近表头的用得越频繁
 * 首先接收一个capacity作为缓存的最大容量，实现两个API put(key, val) 和 get(key)不存在返回-1
 */
//造轮子
//定义一个双链表的节点
class Node {
    public int key, val;
    public Node next, prev;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

//双链表 包含一些必要的API
class DoubleList {
    //表头表尾的虚节点
    Node head, tail;
    //记录链表元素数目
    int size;
    //初始化链表
    public DoubleList() {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }
    //在链表的尾部添加节点x
    public void addlast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }
    //删除链表中的节点Node x  (一定存在x)
    public void remove(Node x) {
        x.next.prev = x.prev;
        x.prev.next = x.next;
        size--;
    }
    //删除链表的第一个节点，并返回该节点。
    public Node removefirst() {
        //如果为空
        if(head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }
}

//LRU
class LRUCache {
    HashMap<Integer, Node> map = new HashMap<>();
    DoubleList cache = new DoubleList();
    int cap;
    public LRUCache(int cap) {
        this.cap = cap;
    }
    //提升到最近使用
    public void makeRecently(int key) {
        Node x = map.get(key);
        cache.remove(x);
        cache.addlast(x);

    }
    //删除某个key
    public void deletekey(int key) {
        Node x = map.get(key);
        cache.remove(x);
        map.remove(key);
    }
    public void addRencently(int key, int val) {
        Node x = new Node(key, val);
        cache.addlast(x);
        map.put(key, x);
    }
    public void removeLeastRecently() {
        Node deletfirst = cache.removefirst();
        int deletedkey = deletfirst.key;
        map.remove(deletedkey);
    }

    //先写一个get方法等会再写用到的函数
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }else
        {
            //如果存在那就将该节点提升到最近使用，并发挥该节点的val
            makeRecently(key);
            return map.get(key).val;
        }
    }
    //put
    public void put(int key, int val) {
        //首先判断是否存在，存在就删掉在加在链尾
        if(map.containsKey(key)) {
            deletekey(key);
            addRencently(key, val);
            return;
        }//如果不存在，满了就删掉最久未使用的节点再操作。
        if(cap == cache.size) {
            removeLeastRecently();
        }
        addRencently(key, val);
    }
}