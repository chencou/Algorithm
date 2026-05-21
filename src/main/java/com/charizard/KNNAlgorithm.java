package com.charizard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * KNNAlgorithm K最近邻算法 演示
 *
 */
public class KNNAlgorithm {

    @Data
    public static class User {

        /**
         * 用户名
         */
        private String name;

        /**
         * 特征向量
         */
        private List<Integer>  features;

        public User(String name, List<Integer> features) {
            this.name = name;
            this.features = features;
        }
    }


    public static void main(String[] args) {
        List<User> users = List.of(
                new User("张三", List.of(1, 2, 3, 4, 5)),
                new User("李四", List.of(6, 6, 7, 7, 7)),
                new User("王五", List.of(3, 4, 5, 6, 7)),
                new User("赵六", List.of(5, 6, 6, 7, 8))
        );
        System.out.println("原始用户为：");
        users.forEach(System.out::println);

        List<Integer> features = List.of(5, 6, 7, 8, 7);
        List<User> knnUsers = KNNUser(users, features, 2);
        System.out.println("KNN算法获取的相邻用户为：");
        knnUsers.forEach(System.out::println);

    }

    /**
     * KNN 最近邻算法 获取相邻的用户
     *
     * @param users
     * @param features
     * @return
     */
    private static List<User> KNNUser(List<User> users, List<Integer> features, int k) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            // 计算距离
            int distance = 0;
            for (int i = 0; i < features.size(); i++) {
                // 计算评分相差结果的平方
                distance +=  (int) Math.pow(user.getFeatures().get(i) - features.get(i), 2);
            }
            //最终结果“√”根号换算比较临近值
            distance = (int) Math.sqrt(distance);
            if (distance <= k) {
                result.add(user);
            }

        }
        return result;
    }
}
