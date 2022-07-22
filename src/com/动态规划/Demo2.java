package com.动态规划;

import java.util.Arrays;
import java.util.Scanner;

public class Demo2 {
    /**
     * 动态规划求解
     * 输入：数量n，各自的重量，各自的价格
     * 输出：背包问题的解
     * 将问题拆解为前i个物品的最优解
     */
    static int[] weight;
    static int[] value;
    static int w;
    static int n;
    static int[][] f;
    static String[][] z;


    public static void main(String[] args) {
        initial();
        f = new int[n + 1][w + 1];//动态规划表 默认全是0 用来得到所选物品编号
        z = new String[n + 1][w + 1];
        Arrays.stream(z).forEach(
                x->Arrays.fill(x,"")
        );//数组初始化为空字符串
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (i == 0 || j == 0)
                    f[i][j] = 0;
                else
                    f[i][j] = -1;
            }
        }
        int maxValue = Mfk(n, w);
        System.out.println("容量为" + w + "千克的背包最多能装价值为" + maxValue);
        System.out.println("所选的物品为" + z[n][w] + "号物品");
    }

    public static int Mfk(int i, int j) {
        int v;
        if (f[i][j] < 0) {
            if (j < weight[i]) {
                v = Mfk(i - 1, j);
                z[i][j] = z[i - 1][j];
            } else {
                //v = Math.max(Mfk(i - 1, j), value[i] + Mfk(i - 1, j - weight[i]));
                if (Mfk(i - 1, j) > value[i] + Mfk(i - 1, j - weight[i])) {
                    v = Mfk(i - 1, j);
                    z[i][j] = z[i - 1][j];
                } else {
                    v = value[i] + Mfk(i - 1, j - weight[i]);
                    z[i][j] = z[i - 1][j - weight[i]] + " " + i;
                }
            }
            f[i][j] = v;
        }
        return f[i][j];
    }

    private static void initial() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入物品数量");
        n = sc.nextInt();//数量
        System.out.println("请输入背包的容量");
        w = sc.nextInt();
        weight = new int[n + 1];//重量
        value = new int[n + 1];//价格
        System.out.println("请依次输入重量和价值");

        for (int i = 1; i <= n; i++) {
            System.out.println(i + "的重量和价格");
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }
        /*for (int i = 0; i < n; i++) {
            System.out.println(weight[i]+"..."+value[i]);
        }*/
    }
}
