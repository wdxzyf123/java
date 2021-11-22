package leetcode;

import java.util.LinkedList;

public class xu_lie_hua_and_fan_xu_lie_hua {
    public static void main(String[] args) {
        TreeNode root5 = new TreeNode(5);
        TreeNode root4 = new TreeNode(4);
        TreeNode root3 = new TreeNode(3);
        TreeNode root2 = new TreeNode(2, root4, root5);
        TreeNode root1= new TreeNode(1, root2, root3);
        precode pre = new precode();
        StringBuilder sb = new StringBuilder();
        pre.serialize(root1, sb);
        System.out.println(sb);
        TreeNode root = pre.deserialize1(sb.toString());

    }
}

class precode {
    String SEP = ",";
    String NULL = "#";
//    StringBuilder sb = new StringBuilder();
    public void serialize(TreeNode root, StringBuilder sb) {
        //前序遍历的序列化
        if(root == null)
        {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }
    public TreeNode deserialize1(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            //去掉逗号建立一个字符串列表
            nodes.addLast(s);
        }
        return deserialize2(nodes);
    }
    public TreeNode deserialize2(LinkedList<String> nodes) {
        if(nodes.isEmpty()) return null;
        String first = nodes.removeFirst();
        if(first.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize2(nodes);
        root.right = deserialize2(nodes);

        return root;
    }


}