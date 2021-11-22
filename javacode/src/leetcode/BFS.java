package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS
 * 一般用来求最小距离
 * 通用框架：
 * static int BFS(Node start, int target){
 *         //一个核心的数据结构，队列
 *         Queue<Node> q;
 *         Set<Node> visited;//维护一个visited避免走回头路，常见的多叉树不用，因为没有指针指向回头路
 *         //将起点加入队列
 *         q.offer(start);
 *         //七点入visited
 *         visited.add(start);
 *         int step = 0;
 *
 *         while(!q.isEmpty()) {
 *             int sz = q.size();
 *             for (int i = 0; i < sz; i++) {
 *                 Node cur = q.poll();
 *                 if(cur == target)
 *                     return step;
 *                 for (Node x: cur.adj()) {
 *                     if( !visited.contains(x))
 *                         q.offer(x);
 *                         visited.add(x);
 *                 }
 *             }
 *         }
 *         step++;
 *     }
 */

public class BFS {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(6);
        tree.right = new TreeNode(5);
        System.out.println(minDepth(tree));
    }
    //求解二叉树的最小高度
    public static int minDepth(TreeNode root) {
        //先来判断空的
        if(root == null) return 0;
        //核心数据结构队列
        Queue<TreeNode> q = new LinkedList<>();
        //根节点入队
        q.offer(root);
        int deep = 1;
        while(!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                //判断是不是叶子
                if(cur.left == null && cur.right == null)
                    return deep;
                //相邻点入队
                if(cur.left != null)
                    q.offer(cur.left);
                if(cur.right != null)
                    q.offer(cur.right);
            }
            deep++;
        }
        return deep;
    }
}
