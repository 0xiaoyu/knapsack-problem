package com.分支界限法;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class Demo2 {
    static int maxValue;//最大价值
    static int n;//数量
    static int capacity;//容量
    static Goods[] goods;
    static TreeSet<Integer> set = new TreeSet<>();//最优解
    static PriorityQueue<Node> pq = new PriorityQueue<>();//节点优先队列
    static PriorityQueue<Goods> gpq = new PriorityQueue<>();//物品优先队列

    static {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请输入物品数量");
            n = sc.nextInt();
            System.out.println("请输入背包的容量");
            capacity = sc.nextInt();
            if (n<0){
                System.out.println("物品数量输入不合法");
                continue;
            }
            break;
        }
        System.out.println("请依次输入重量和价值");
        goods=new Goods[n];
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "的重量和价格");
            Double weight = sc.nextDouble();
            Double value = sc.nextDouble();
            Goods goods = new Goods(weight, value, i + 1);
            gpq.add(goods);
        }
        for (int i = 0; i < n; i++) {
            goods[i]=gpq.poll();
        }
        Node initial = new Node();
        initial.level = -1;
        initial.up = Bound(initial);
        pq.add(initial);
    }

    //求解上线
    public static double Bound(Node no) {
        double max = no.value;
        int leftWeight = capacity - no.weight;
        int t = no.level + 1;
        //不能装时，用下一个物品的单位重量价值折算到剩余空间。
        if (t <= n - 1) {
            max += goods[t].value / goods[t].weight * leftWeight;
        }
        return max;
    }

    public static void main(String[] args) {
        while (!pq.isEmpty()) {
            Node fatherNode = pq.poll();
            if (fatherNode.level == n - 1) {
                if (fatherNode.value > maxValue) {
                    maxValue = (int) fatherNode.value;
                    for (int i = n - 1; i >= 0; i--) {
                        set.add(fatherNode.id);
                        fatherNode = fatherNode.father;
                    }
                }
            } else {
                if (goods[fatherNode.level + 1].weight + fatherNode.weight <= capacity && fatherNode.up > maxValue) {
                    Node newNode = new Node();
                    newNode.level = fatherNode.level + 1;
                    newNode.weight = (int) (goods[fatherNode.level + 1].weight + fatherNode.weight);
                    newNode.value = goods[fatherNode.level + 1].value + fatherNode.value;
                    newNode.father = fatherNode;
                    newNode.Left = 1;
                    newNode.up = Bound(newNode);
                    newNode.id = goods[fatherNode.level + 1].id;
                    if (newNode.up > maxValue)
                        pq.add(newNode);
                }
                if ((fatherNode.up - goods[fatherNode.level + 1].value) > maxValue) {
                    Node newNode2 = new Node();
                    newNode2.level = fatherNode.level + 1;
                    newNode2.value = fatherNode.value;
                    newNode2.weight = fatherNode.weight;
                    newNode2.father = fatherNode;
                    newNode2.up = Bound(newNode2);
                    newNode2.Left = 0;
                    pq.add(newNode2);
                }
            }
        }
        set.remove(0);
        System.out.println("该背包能够取到的最大价值为:" + maxValue);
        System.out.println("选取的物品为" + set);
    }

}
