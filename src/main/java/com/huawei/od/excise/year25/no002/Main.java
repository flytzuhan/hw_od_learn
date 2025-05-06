package com.huawei.od.excise.year25.no002;

import java.util.*;

/**
 * 模拟消息队列
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] tmp2 = line.split(" ");
        // 处理所有的消息发送者
        int[] nums = new int[tmp2.length];
        for (int i = 0; i < tmp2.length; i++) {
            nums[i] = Integer.parseInt(tmp2[i]);
        }

        // 获取所有的消息消费者
        String nextLine = in.nextLine();
        String[] tmp1 = nextLine.split(" ");
        int[] nums1 = new int[tmp1.length];
        for (int i = 0; i < tmp1.length; i++) {
            nums1[i] = Integer.parseInt(tmp1[i]);
        }

        int n = nums.length;
        int m = nums1.length;

        // 发布消息
        Message[] producerMap = new Message[n / 2];
        int index = 0;
        for (int i = 0; i < n; i += 2) {
            producerMap[index++] = new Message(nums[i], nums[i + 1]);
        }

        // 对消息进行排列
        Arrays.sort(producerMap, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.a - o2.a;
            }
        });

        // 消费消息
        Message[] consumerMap = new Message[m / 2];
        int index1 = 0;
        for (int i = 0; i < m; i += 2) {
            consumerMap[index1++] = new Message(nums1[i], nums1[i + 1]);
        }

        // 构建消费关系
        List<List<Integer>> relationMap = new ArrayList<>();
        for (int j = 0; j < m / 2; j++) {
            relationMap.add(new ArrayList<>());
        }

        // 遍历发布者
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2 - 1; j >= 0; j--) {
                if (producerMap[i].a >= consumerMap[j].a) {
                    if (producerMap[i].a < consumerMap[j].b) {
                        relationMap.get(j).add(producerMap[i].b);
                        break;
                    }
                }
            }
        }

        int i = 0;
        while (true) {
            if (i >= m / 2) {
                break;
            } else {
                if (relationMap.get(i).isEmpty()) {
                    System.out.println("-1");
                } else {
                    String result = "";
                    for (Integer x : relationMap.get(i)) {
                        result += x + " ";
                    }
                    System.out.println(result);
                }
            }
            i += 1;
        }

        return;
    }

    static class Message {
        int a;
        int b;

        public Message(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
