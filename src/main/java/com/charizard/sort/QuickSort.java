package com.charizard.sort;

import java.util.*;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        numList.add(33);
        numList.add(22);
        numList.add(3);
        numList.add(6);
        numList.add(11);
        numList.add(55);
        numList.add(7);

        numList = quitSort(numList);

        numList.forEach(System.out::println);
    }

    public static List<Integer> quitSort(List<Integer> arrays){
        if (Objects.isNull(arrays) || arrays.isEmpty()) {
            return new ArrayList<>();
        }
        if (arrays.size() < 2) {
            return arrays;
        }
        Integer num = arrays.get(0);
        //筛选小于基数的值
        List<Integer> lessList = arrays.stream().filter(item -> item < num).toList();

        //筛选大于基数的值
        List<Integer> greaterList = arrays.stream().filter(item -> item > num).toList();

        List<Integer> endArrays = new ArrayList<>();
        List<Integer> returnLessQuitSort = quitSort(lessList);
        if (!returnLessQuitSort.isEmpty()) {
            endArrays.addAll(returnLessQuitSort);
        }
        endArrays.add(num);
        List<Integer> returnGreaterQuitSort = quitSort(greaterList);
        if (!returnGreaterQuitSort.isEmpty()) {
            endArrays.addAll(returnGreaterQuitSort);
        }
        return endArrays;
    }
}
