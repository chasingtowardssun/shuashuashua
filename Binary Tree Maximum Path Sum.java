import apple.laf.JRSUIUtils;

import java.util.*;

public class PocketGems {
    static Node maxSum = new Node(Integer.MIN_VALUE, new ArrayList<>());
    static class Node {
        int curSum;
        List<String> curPath;
        public Node(int curSum, List<String> curPath) {
            this.curSum = curSum;
            this.curPath = new ArrayList<>(curPath);
        }

        public void print() {
            System.out.println("The max Sum is " + curSum);
            System.out.println("The max Sum path is " + String.join("->", curPath));
        }
    }
    public static Node maxSumPath(TreeNode root) {
        helper(root);
        return maxSum;
    }
    private static Node getMaxNode(Node left, Node right) {
        if (left.curSum > right.curSum) return left;
        return right;
    }
    private static Node helper(TreeNode root) {
        if (root == null) return new Node(0, new ArrayList<>());

        Node left = getMaxNode(helper(root.left), new Node(0, new ArrayList<>()));
        Node right = getMaxNode(helper(root.right), new Node(0, new ArrayList<>()));
        List<String> temp = new ArrayList<>();
        temp.addAll(left.curPath);
        temp.add(root.val + "");

        Collections.reverse(right.curPath);

        temp.addAll(right.curPath);
        Collections.reverse(right.curPath);
        maxSum = getMaxNode(maxSum, new Node(left.curSum + right.curSum + root.val, temp));

        if (left.curSum >= right.curSum) {
            left.curSum += root.val;
            left.curPath.add(root.val + "");
            return left;
        } else {
            right.curSum += root.val;
            right.curPath.add(root.val + "");
            return right;

        }
    }

    public static void main(String[] args) {
        System.out.println("Hello");

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(13);
        root1.left.right.left = new TreeNode(5);
        root1.right.left = new TreeNode(2);
        root1.right.left.right = new TreeNode(7);
        root1.right.right = new TreeNode(-5);
        Node res1 = maxSumPath(root1);
        res1.print();
        //System.out.println(res1.print());
    }
}
//---------------
public class pathSum_RootLeaf {
    public List<TreeNode> findpath(TreeNode root) {
        Node res = helper(root);
        if (res == null) return new ArrayList<>();
        List<TreeNode> cur = res.list;
        Collections.reverse(cur);
        return cur;
    }
    //To Leaf
    public Node helper(TreeNode root) {
        if (root == null) return null;
        Node left = helper(root.left);
        Node right = helper(root.right);
        if (left == null && right == null) {
            Node cur = new Node(root.val);
            cur.list.add(root);
            return cur;
        }
        if (left == null || (right != null && right.sum > left.sum)) {
            right.sum = right.sum + root.val;
            right.list.add(root);
            return right;
        } else {
            left.sum = left.sum + root.val;
            left.list.add(root);
            return left;
        }
    }
    //To any node in the tree 
    public Node backtrack(TreeNode root) {
        if (root == null) return null;
        Node left = backtrack(root.left);
        Node right = backtrack(root.right);
        if (left == null && right == null) {
            Node cur = new Node(root.val);
            cur.list.add(root);
            return cur;
        }
        if(left == null || (right != null && right.sum > left.sum)) {
            if (right.sum < 0) {
                right = new Node(root.val);
            } else {
                right.sum += root.val;
            }
            right.list.add(root);
            return right;
        } else {
            if (left.sum < 0) {
                left = new Node(root.val);
            } else {
                left.sum += root.val;
            }
            left.list.add(root);
            return left;
        }
    }
    class Node {
        int sum;
        List<TreeNode> list;
        public Node(int sum) {
            this.sum = sum;
            list = new ArrayList<>();
        }
    }
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode (int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
}
