package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class mi_ma_suo {
    public static void main(String[] args) {
        String target = "9999";
        String[] deadends = {"2894"};
        System.out.println(open(deadends, target));
    }
    public static String up(String s, int j) {
        char[] ch = s.toCharArray();
        if(ch[j] == '9')
            ch[j] = '0';
        else
            ch[j]++;
        return new String(ch);
    }
    public static String dowm(String s, int j) {
        char[] ch = s.toCharArray();
        if(ch[j] == '0')
            ch[j] = '9';
        else
            ch[j]--;
        return new String(ch);
    }
    //求能解开的最小次数，分析知道每次有8中可能，映射成一幅图，可以利用BFS解决
    public static int open(String[] deadends, String target) {
        //核心数据结构队列
        Queue<String> q = new LinkedList<>();
        //维护一个visited集合
        Set<String> visited = new HashSet<>();
        //死亡密码转成set
        Set<String> deads = new HashSet<>();
        for (String dead : deadends) {
            deads.add(dead);
        }
        //初始化步骤
        int step = 0;
        //初始密码入队
        q.offer("0000");
        //初始密码维护visited
        visited.add("0000");
        while(!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                //合法性判断
                if(deads.contains(cur))
                    continue;
                if(cur.equals(target))
                    return step;
                for (int j = 0; j < 4; j++) {
                    String up = up(cur, j);
                    if(!visited.contains(up))
                        q.offer(up);
                        visited.add(up);
                    String dowm = dowm(cur, j);
                    if(!visited.contains(dowm))
                        q.offer(dowm);
                    visited.add(dowm);

                }
            }
            step++;
        }
        return -1;//穷举完都没找到
    }
}
