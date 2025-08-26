package com.huawei.od.excise.year25.c.n012;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 计算三叉树的高度 -- 递归算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 先构造二叉树
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < n; i++) {
            insert(root, nums[i]);
        }
        // 现在计算三叉树的高度
        System.out.println(getHeight(root));
    }

    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.getLeft());
        int rightHeight = getHeight(root.getRight());
        int midHeight = getHeight(root.getMid());
        return Math.max(leftHeight, Math.max(rightHeight, midHeight)) + 1;
    }

    public static void insert(TreeNode root, int value) {
        if (value < root.getValue()-500) {
            if (root.getLeft() == null) {
                root.setLeft(new TreeNode(value));
            } else {
                insert(root.getLeft(), value);
            }
        } else if (value > root.getValue() + 500) {
            if (root.getRight() == null) {
                root.setRight(new TreeNode(value));
            } else {
                insert(root.getRight(), value);
            }
        } else {
            if (root.getMid() == null) {
                root.setMid(new TreeNode(value));
            } else {
                insert(root.getMid(), value);
            }
        }
    }

    public static class TreeNode {
        private TreeNode left;
        private TreeNode mid;
        private TreeNode right;
        private int value;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getMid() {
            return mid;
        }

        public void setMid(TreeNode mid) {
            this.mid = mid;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
