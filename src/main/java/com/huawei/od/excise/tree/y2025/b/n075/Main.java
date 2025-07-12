package com.huawei.od.excise.tree.y2025.b.n075;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 75. 二叉树的广度优先遍历 -- BFS
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] params = in.nextLine().split(" ");
        System.out.println(bfs(params[0], params[1]));
    }

    private static String bfs(String postOrder, String inOrder) {
        if (postOrder.isEmpty() || inOrder.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        // 存储待处理的节点
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, postOrder.length() - 1, 0, inOrder.length() - 1});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                int postStart = node[0], postEnd = node[1];
                int inStart = node[2], inEnd = node[3];
                if (postStart > postEnd || inStart > inEnd) {
                    continue;
                }
                // 根节点
                char rootNode = postOrder.charAt(postEnd);
                result.append(rootNode);
                // 在中序遍历中找到根节点
                int rootIndex = inOrder.indexOf(rootNode, inStart);
                int leftSize = rootIndex - inStart;

                // 将左子树加入到队列中
                if (leftSize > 0) {
                    queue.add(new int[]{postStart, postStart + leftSize - 1, inStart, rootIndex - 1});
                }
                if (rootIndex < inEnd) {
                    queue.add(new int[]{postStart + leftSize, postEnd - 1, rootIndex + 1, inEnd});
                }
            }
        }

        return result.toString();
    }
}
