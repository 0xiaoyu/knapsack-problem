package com.穷举法;

import java.util.Arrays;
import java.util.Scanner;

public class Demo1 {
    /**
     * 枚举算法
     * 枚举有n个物品 每一个物品都有选和不选2种结果，所有有2^n的结果
     * 可以通过二进制来表达这所有结果，然后遍历所有结果
     */
    static int weight[];//重量
    static int value[];//价格
    static int n;//数量
    static int w;//容量
    static int[][] a;//穷举列表
    static int z;//总数目
    static int valueMax = 0;//最大价值


    public static void main(String[] args){
        while (true){
            try {
                initial();
            } catch (Exception e) {
                System.out.println("输入有误");
                continue;
            }
            z = (int) Math.pow(2, n);
            a = new int[z][n];
            all(z);
        /*Arrays.stream(a).forEach(
                x->System.out.println(Arrays.toString(x)));
        }*/
            int best = findMax();
            System.out.println("所有物品的总价值为"+valueMax);
            System.out.println("物品所选情况为"+Arrays.toString(a[best]));
            break;
        }

    }

    /**
     * 得到所有的可能情况
     * @param z 物品总数目
     * 将得到是所有情况放入到全局变量a数组中
     */
    public static void all(int z) {
        for (int i = 0; i < z; i++) {
            int x = i;
            for (int j = 0; j < n; j++) {
                if (x != 0) {
                    a[i][j] = x % 2;
                    x /= 2;
                } else {
                    a[i][j] = 0;
                }
            }
        }
    }


    /**
     * 遍历所有的可能来找到最优解
     * 返回最好的那个可能情况的索引
     */
    private static int findMax() {
        int best=0;
        int allWeight = 0;
        int allValue = 0;
        int no=0;
        for (int i = 0; i < z; i++) {//所有排列的循环
            allWeight = 0;
            allValue = 0;
            no=0;
            for (int j = 0; j < n; j++) {//对改排列的求解
                if (a[i][j] == 1) {
                    allWeight += weight[j];
                    allValue += value[j];
                }
                if (allWeight > w) {
                    no=1;
                    break;
                }
            }
            if (no==0 && allValue > valueMax) {
                best = i;
                valueMax = allValue;
            }
        }
        return best;
    }

    //初始化所有变量
    private static void initial() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入物品数量");
        n = sc.nextInt();
        System.out.println("请输入背包的容量");
        w = sc.nextInt();
        if(n<0||w<0)
            throw new Exception() ;
        weight = new int[n];
        value = new int[n];
        System.out.println("请依次输入重量和价值");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "的重量和价格");
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
            if(weight[i]<0||value[i]<0)
                throw new Exception() ;
        }
    }
}
