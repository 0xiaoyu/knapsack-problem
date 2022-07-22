package com.分支界限法;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Demo1 {
    static int maxValue;//最大价值
    static int[] weight;//重量
    static int[] value;//价值
    static int n;//数量
    static int capacity;//容量
    static int[] bestWay;//最优解
    static PriorityQueue<Node> pq = new PriorityQueue<>();//优先队列

    static {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入物品数量");
        n = sc.nextInt();
        System.out.println("请输入背包的容量");
        capacity = sc.nextInt();
        weight = new int[n];
        value = new int[n];
        bestWay = new int[n];
        System.out.println("请依次输入重量和价值");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "的重量和价格");
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
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
            max += value[t] / weight[t] * leftWeight;
        }
        return max;
    }

    public static void main(String[] args) {
        initial(weight, value);
        /*System.out.println(Arrays.toString(weight));
        System.out.println(Arrays.toString(value));*/
        while (!pq.isEmpty()) {
            Node fatherNode = pq.poll();
            if (fatherNode.level == n - 1) {
                if (fatherNode.value > maxValue) {
                    maxValue = (int) fatherNode.value;
                    for (int i = n - 1; i >= 0; i--) {
                        bestWay[i] = fatherNode.Left;
                        fatherNode = fatherNode.father;
                    }
                }
            } else {
                //如果下一个可以放入，则处理数据，然后计算最大上界
                if (weight[fatherNode.level + 1] + fatherNode.weight <= capacity && fatherNode.up > maxValue) {
                    Node newNode = new Node();
                    newNode.level = fatherNode.level + 1;
                    newNode.weight = weight[fatherNode.level + 1] + fatherNode.weight;
                    newNode.value = value[fatherNode.level + 1] + fatherNode.value;
                    newNode.father = fatherNode;
                    newNode.Left = 1;
                    newNode.up = Bound(newNode);
                    if (newNode.up > maxValue)
                        pq.add(newNode);
                }
                //如果下一个放不下，则为上一个一样
                if ((fatherNode.up - value[fatherNode.level + 1]) > maxValue) {
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
        System.out.println("该背包能够取到的最大价值为:" + maxValue);
        int z = 0;
        ArrayList<Integer> a = new ArrayList<>();
        for (int best : bestWay) {
            if (best == 1)
                a.add(value[z]);
            z++;
        }
        System.out.println("选取价值为" + a + "的物品");
    }


    public static void initial(int[] weight, int[] value) {
        int n = weight.length;
        int[] c = new int[n];//单位价格
        for (int i = 0; i < n; i++) {
            c[i] = value[i] / weight[i];
        }
        for (int j = 0; j < n - 1; j++) {
            int max = j;
            for (int i = j + 1; i < n; i++) {
                if (c[max] < c[i])
                    max = i;
            }
            swap(weight, j, max);
            swap(value, j, max);
            swap(c, j, max);
        }

    }

    //交换数组的2位
    public static void swap(int[] weight, int x, int y) {
        if (x != y) {
            int z = weight[x];
            weight[x] = weight[y];
            weight[y] = z;
        }
    }
}
