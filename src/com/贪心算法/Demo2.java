package com.贪心算法;

import java.util.*;

public class Demo2 {
    /**
     * 贪心算法
     * 算出价值与重量比
     * 每次选择价值与重量比最大的装入背包
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入物品数量");
        int n = sc.nextInt();//数量
        System.out.println("请输入背包的容量");
        Double m = sc.nextDouble();//总容量

        Double maxValue = 0.0;//装下价值

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        System.out.println("请依次输入重量和价值");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "的重量和价格");
            Double a = sc.nextDouble();
            Double b = sc.nextDouble();
            Node node = new Node(a, b, i + 1);
            priorityQueue.add(node);
        }

        TreeSet<Integer> set = new TreeSet<>();
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (node.weight <= m) {
                maxValue += node.value;
                m -= node.weight;
                set.add(node.id);
            }
        }
        System.out.println("背包所能装下的最大价值为"+maxValue);
        System.out.println("商品的选择为"+set);
    }
}
