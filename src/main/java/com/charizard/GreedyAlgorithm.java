package com.charizard;

import java.util.ArrayList;
import java.util.List;

/**
 * 贪心算法
 *
 * <p>贪心算法是一种在每一步选择中都采取当前最佳选择，从而达到全局最佳的选择算法。</p>
 * <p>贪心算法适用于问题：</p>
 * <ul>
 *     <li>问题可以分解为多个子问题</li>
 *     <li>子问题之间没有依赖关系</li>
 *     <li>子问题可以任意顺序求解</li>
 *     <li>子问题求解过程中，每次选择当前最佳的选择</li>
 * </ul>
 *
 */
public class GreedyAlgorithm {

    public static class Activity {
        public int start;
        public int end;
        public String name;
        public Activity(int start, int end, String name) {
            this.start = start;
            this.end = end;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        // 活动列表
        List<Activity> activityList = new ArrayList<>();
        //开始时间，结束时间，活动名称
        activityList.add(new Activity(1, 4, "a"));
        activityList.add(new Activity(3, 5, "b"));
        activityList.add(new Activity(5, 7, "d"));
        activityList.add(new Activity(5, 6, "e"));
        activityList.add(new Activity(8, 9, "f"));

        // 最后时间
        int lastTime = 0;

        for (Activity activity : activityList) {
            if (activity.start >= lastTime) {
                System.out.println("活动名称：" + activity.name + "时间" + activity.start + "~" + activity.end);
                lastTime = activity.end;
            }
        }
    }
}
