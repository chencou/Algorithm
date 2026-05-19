package com.charizard;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 动态规划 示例
 * @author charizard
 * @since 2026/05/18
 */
public class DynamicProgramming {

    @Data
    public  static class Item {
        private String name;
        private int weight;
        private int price;

        public Item(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", price=" + price +
                    '}';
        }

    }


    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("水", 3, 10));
        itemList.add(new Item("书", 1, 3));
        itemList.add(new Item("食物", 2, 9));
        itemList.add(new Item("夹克", 2, 5));
        itemList.add(new Item("相机", 1, 6));

        int backpack = 6;

        int maxPrice = getDPMaxPrice(itemList, backpack);
        System.out.println("背包最大价值为：" + maxPrice);

        String word1 = "apple";
        String word2 = "application";

        int editDistance = getDPSameWord(word1, word2);
        System.out.println("word1: " + word1);
        System.out.println("word2: " + word2);
        System.out.println("相同单词数量：" + editDistance);
    }

    /**
     * 获取相同单词数量
     * @param word1
     * @param word2
     * @return
     */
    private static int getDPSameWord(String word1, String word2) {

        int[][] dp = new int[word1.length()][word2.length() + 1];

        //List<Character> flagList = new ArrayList<>();

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                //if (flagList.contains(word1.charAt(i))) continue;
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = i <= 0 || j <= 0 ? 1 : dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] =  i <= 0 || j <= 0 ? 0 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[word1.length() - 1][word2.length() -1];
    }

    /**
     * 获取背包最大价值
     * @param itemList 物品列表
     * @param backpack 背包容量
     * @return 最大价值
     */
    public static int getDPMaxPrice(List<Item> itemList, int backpack) {
        if (itemList == null || itemList.isEmpty()) {
            return 0;
        }
        int n = itemList.size();
        int[][] dp = new int[n][backpack + 1];

        for (int i = 0; i < n; i++) {
            int itemWeight = itemList.get(i).getWeight();
            for (int j = 0; j <= backpack; j++) {
                if (i == 0) {
                    dp[i][j] = (itemWeight <= j) ? itemList.get(i).getPrice() : 0;
                } else {
                    if (itemWeight > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(
                                dp[i - 1][j],
                                itemList.get(i).getPrice() + dp[i - 1][j - itemWeight]
                        );
                    }
                }
            }
        }
        return dp[n - 1][backpack];
    }


}
