package com.huawei.od.excise.last;

import java.util.*;

/**
 * 模拟消息队列
 */
public class MockMessageQueueMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 首先是获取第一行数据，用来获取消息的内容
        Map<Integer, Integer> messageMap = new HashMap<>();
        int[] messages = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 现在开始将这些消息存储到map中
        for (int i = 0; i < messages.length; i+=2) {
            messageMap.put(messages[i], messages[i+1]);
        }
        // 现在再来获取订阅和取消订阅的数据
        int[] personData = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int personNumber = personData.length / 2;
        int[] subStart = new int[personNumber];
        int[] subEnd = new int[personNumber];
        // 现在把时间存储起来
        for (int i = 0; i < personNumber; i++) {
            subStart[i] = personData[i * 2];
            subEnd[i] = personData[i * 2 + 1];
        }

        // 现在要讲所有的时间获取到
        Set<Integer> times = new HashSet<>(messageMap.keySet());
        for (int i = 0; i < personNumber; i++) {
            times.add(subStart[i]);
            times.add(subEnd[i]);
        }

        // 现在获取到所有的时刻，然后对这个进行排序
        List<Integer> timeList = new ArrayList<>(times);
        Collections.sort(timeList);

        // 定义列表用来存储每个订阅人的消息
        List<List<String>> consumerMessageList = new ArrayList<>();
        for (int i = 0; i < personNumber; i++) {
            consumerMessageList.add(new ArrayList<>());
        }

        // 订阅人的数据，这要支持从末尾添加和获取
        Deque<Integer> subscribe = new LinkedList<>();

        // 现在开始循环这些数据
        for (Integer time : timeList) {
            // 先处理订阅时刻的数据
            for (int i = 0; i < personNumber; i++) {
                if (subStart[i] == time) {
                    // 添加订阅人
                    subscribe.addLast(i);
                }
            }
            // 再处理取消订阅的数据
            for (int i = 0; i < personNumber; i++) {
                if (subEnd[i] == time) {
                    subscribe.remove(i);
                }
            }
            // 最后处理消息内容的数据
            if (messageMap.containsKey(time)) {
                // 这个时刻有消息，判断订阅人数据是否为空，为空的话，直接丢弃这条消息，如果不为空的话，让优先级最高的人先消费
                if (!subscribe.isEmpty()) {
                    Integer content = messageMap.get(time);
                    Integer subscribeLast = subscribe.getLast();
                    consumerMessageList.get(subscribeLast).add(String.valueOf(content));
                }
            }
        }

        // 现在开始可以打印订阅人的消息了
        for (int i = 0; i < personNumber; i++) {
            if (consumerMessageList.get(i).isEmpty()) {
                System.out.println("-1");
            } else {
                System.out.println(String.join(" ", consumerMessageList.get(i)));
            }
        }
    }
}
