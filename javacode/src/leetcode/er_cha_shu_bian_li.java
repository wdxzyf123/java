package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class er_cha_shu_bian_li {
    public static void main(String[] args){
        TreeNode root5 = new TreeNode(5);
        TreeNode root4 = new TreeNode(4);
        TreeNode root3 = new TreeNode(3);
        TreeNode root2 = new TreeNode(2, root4, root5);
        TreeNode root1= new TreeNode(1, root2, root3);
//        prebianli(root1);
        ceng_cib_bian_li(root1);
    }
    public static  void prebianli(TreeNode root) {
        if(root == null) return;
        System.out.println(root.val);
        prebianli(root.left);
        prebianli(root.right);
    }
    public static void ceng_cib_bian_li (TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty())
        {
            TreeNode cur = q.poll();
            if( cur != null)
            {
                System.out.println(cur.val);
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val){this.val = val;}
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    }

