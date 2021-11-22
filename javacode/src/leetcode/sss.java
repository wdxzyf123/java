package leetcode;
import java.util.HashMap;
import java.util.LinkedList;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class sss{
    static HashMap<String, Integer> memo = new HashMap<>();
    static LinkedList<TreeNode> res = new LinkedList<>();
    static String str = new String();

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(4);
//        root.right = new TreeNode(4);
//        root.left.left = new TreeNode(9);
//        root.left.right = new TreeNode(4);
//        root.right.left = new TreeNode(9);
//        root.right.right = new TreeNode(4);
//        LinkedList<TreeNode> pr = new LinkedList<>();
//        serialize_compare(root);
//        System.out.println(res.size());
        for (int i = 2; i < 2; i++) {
            System.out.println(i);
        }
    }

    public static String serialize_compare(TreeNode root) {
        if(root == null)
        {
            return "#";
        }

        String left = serialize_compare(root.left);
        String right = serialize_compare(root.right);

//        str.append(root.val).append(",");
        String sub = left + "," + right+ "," + root.val;
        // String s = str.toString();
        int flag = memo.getOrDefault(sub,0);
        if(flag == 1){
            res.add(root);
        }
        memo.put(sub,flag+1);
        return sub;
    }
}