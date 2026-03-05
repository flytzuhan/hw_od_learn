package com.huawei.od.excise.last;

import java.util.*;

/**
 * 斗地主之顺子 -- 100
 */
public class LandlordStraightMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先设置默认的顺子数组
        String[] straight = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        // 使用哈希来存储牌面对应的索引位置
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < straight.length; i++) {
            map.put(straight[i], i);
        }
        // 现在开始从命令行中获取牌面的数据
        String[] cards = in.nextLine().split(" ");
        // 考虑如何开始找，先根据牌面来转成对应的索引,而且要剔除2和重复的数据，所以就要使用Set来存储数据
        Set<Integer> cardIndexSet = new TreeSet<>();
        for (String card : cards) {
            if (map.containsKey(card)) {
                cardIndexSet.add(map.get(card));
            }
        }

        // TreeSet本身就是已经排好序的，因此这里就可以直接使用
        List<Integer> cardIndexList = new ArrayList<>(cardIndexSet);
        // 先处理边界条件
        if (cardIndexList.size() < 5) {
            System.out.println("No");
            return;
        }
        // 定义查找的索引列表
        List<List<Integer>> findIndexList = new ArrayList<>();
        // 现在已经有超过5张牌了，可以处理了
        int startIndex = 0;
        for (int i = 1; i <= cardIndexList.size(); i++) {
            // 处理边界条件
            if (i < cardIndexList.size() && cardIndexList.get(i) == cardIndexList.get(i - 1) + 1) {
                continue;
            } else {
                // 到这里说明要断了，那就是要更新索引的起始位置
                int length = i - startIndex;
                if (length >= 5) {
                    // 满足长度要求，因此可以存储起来
                    findIndexList.add(cardIndexList.subList(startIndex, i));
                }
                startIndex = i;
            }
        }

        // 现在要把这个找到的索引列表给转成牌面列表
        if (findIndexList.isEmpty()) {
            System.out.println("No");
            return;
        }
        for (List<Integer> findIndex : findIndexList) {
            List<String> findCardList = new ArrayList<>();
            for (Integer index : findIndex) {
                findCardList.add(straight[index]);
            }
            System.out.println(String.join(" ", findCardList));
        }
    }
}
