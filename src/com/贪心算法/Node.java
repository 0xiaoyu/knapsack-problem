package com.贪心算法;

public class Node implements Comparable<Node> {
    Double weight;
    Double value;
    Double vw;
    int id;

    public Node(Double weight, Double value, int id) {
        this.weight = weight;
        this.value = value;
        this.id = id;
        this.vw = value / weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", value=" + value +
                ", vw=" + vw +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        if (this.vw > o.vw)
            return -1;
        else if (this.vw < o.vw)
            return 1;
        else
            return 0;
    }
}
