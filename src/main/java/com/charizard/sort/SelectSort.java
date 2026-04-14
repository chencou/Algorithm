package com.charizard.sort;

import java.util.*;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        numList.add(33);
        numList.add(22);
        numList.add(44);
        numList.add(11);
        numList.add(55);
        numList.add(9);
        List<Integer> newNumList = new ArrayList<>(numList.size());

        while (numList.iterator().hasNext()) {
            int selectIndex = 0;
            Integer selectNum = numList.get(selectIndex);
            for (int j = 0; j < numList.size(); j++) {
                if (selectNum < numList.get(j)) {
                    selectIndex = j;
                    selectNum = numList.get(j);
                }
            }
            numList.remove(selectIndex);
            newNumList.add(selectNum);
        }
        //选择排序
        newNumList.forEach(System.out::println);
    }
}