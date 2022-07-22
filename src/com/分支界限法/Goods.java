package com.分支界限法;

public class Goods implements Comparable<Goods>{
    Double weight;
    Double value;
    Double vw;
    int id;

    public Goods(Double weight, Double value, int id) {
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
    public int compareTo(Goods o) {
        if (this.vw > o.vw)
            return -1;
        else if (this.vw < o.vw)
            return 1;
        else
            return 0;
    }
}
