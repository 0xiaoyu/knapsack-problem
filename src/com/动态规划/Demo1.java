package com.动态规划;

import java.util.Scanner;

public class Demo1 {
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


    public static void main(String[] args) {
        initial();

        int[][] c = new int[n + 1][w + 1];//动态规划表 默认全是0 用来得到所选物品编号
        String[][] z = new String[n + 1][w + 1];
        for (int i = 0; i < n + 1; i++) {
            z[0][i] = "";
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                //如果能够承受这个重量
                if (j - weight[i] >= 0) {
                    //c[i][j] = Math.max(c[i - 1][j], value[i] + c[i - 1][j - weight[i]]);
                    int p = c[i - 1][j] - (value[i] + c[i - 1][j - weight[i]]);
                    if (p > 0) {
                        c[i][j] = c[i - 1][j];
                        z[i][j] = z[i - 1][j];
                    } else {
                        c[i][j] = value[i] + c[i - 1][j - weight[i]];
                        z[i][j] = z[i - 1][j - weight[i]] + " " + i;
                    }
                } else {
                    c[i][j] = c[i - 1][j];
                    z[i][j] = z[i - 1][j];
                }
            }
        }
        /*Arrays.stream(c).forEach(//stream流获取每个一维数组
                x -> {
                    System.out.println(Arrays.toString(x));//对每个数组进行打印
                }
        );*/
        System.out.println("容量为" + w + "千克的背包最多能装价值为" + c[n][w]);
        System.out.println("所选的物品为" + z[n][w] + "号物品");
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
