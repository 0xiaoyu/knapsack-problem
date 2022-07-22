package com.分支界限法;

public class Node implements Comparable<Node>{
    int weight;//该节点目前背包中的重量
    double value;//该节点目前背包中的总价值
    double up;//该节点能够达到的价值上界
    int Left;    //该节点是否属于左节点（用于最终构造最优解）
    int level;  //该节点是第几个物品的选择
    Node father; //该节点的父节点
    int id;

    @Override
    public int compareTo(Node Node) {
        if (this.up < Node.up)
            return 1;
        else if (this.up == Node.up)
            return 0;
        else
            return -1;
    }
}
