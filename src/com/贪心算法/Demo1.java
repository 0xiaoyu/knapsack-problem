package com.贪心算法;

import java.util.*;

public class Demo1 {
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
        Double m=sc.nextDouble();//总容量

        Double[] a = new Double[n];//重量
        Double[] b = new Double[n];//价格
        Double value = 0.0;//装下价值
        ArrayList<Integer> z=new ArrayList<>();

        System.out.println("请依次输入重量和价值");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "的重量和价格");
            a[i] = sc.nextDouble();
            b[i] = sc.nextDouble();
        }

        Map<Double, Integer> c = new TreeMap<>((o1, o2) -> o2 > o1 ? 1 : -1);
        /*Float[] c = new Float[n];
        for (int i = 1; i <= n; i++) {//精确计算
            //c[i]= Float.valueOf(b[i]/a[i]);
            BigDecimal c2 = BigDecimal.valueOf(b[i]).divide(BigDecimal.valueOf(a[i]),2,BigDecimal.ROUND_CEILING);
            System.out.println(c2);
        }*/
        for (int i = 0; i < n; i++) {
            c.put(b[i] / a[i], i);
        }
        //System.out.println(c);
        for (Map.Entry<Double, Integer> entry : c.entrySet()) {
            Integer v = entry.getValue();
            if (a[v] <= m){
                m-=a[v];
                value+=b[v];
                z.add(v+1);
            }
        }
        System.out.println("背包最多能装价值为" + value);
        System.out.println("所选的物品为"+z+"号物品");
    }
}
