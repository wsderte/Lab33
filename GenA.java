package com.gmail.Genetic.embeded_lab_3_3;

import java.util.ArrayList;
import java.util.Random;

public class GenA {
    private int a, b, c, d, y;
   public int m = 0;
    GenA(int a, int b, int c, int d, int y) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.y = y;
    }

    public String algorithm() {

        Random r = new Random();
        Double mutation = 0.6;
        ArrayList<ArrayList<Integer>> population = new ArrayList<>();
        Double[] range = new Double[5];
        range[0] = 0.0;
        range[4] = 100.0;
        Integer[] parentsid = new Integer[4];
        Integer[][] children = new Integer[4][4];
        int x = -1;

        getM(population);

        for (int iter = 0; iter  < 0.5 * Integer.MAX_VALUE; iter ++) {
            Integer[] values = new Integer[4];
            Double p = 0.0;

            for (int i = 0; i < population.size(); i++) {
                values[i] = getY(population.get(i));
                int delta = values[i] - y;
                  if (delta == 0) {
                     return  population.get(i).get(0) + " " + population.get(i).get(1) + " " + population.get(i).get(2) + " " + population.get(i).get(3) + " Кл-во мутаций " + m;
                  }
                p = p + 1 / values[i];
            }

            getRange(range, values, p);

            getParent(parentsid,range);

            getR( population, r, mutation, children, parentsid);

            population = new ArrayList<>(4);
            getChild(population, children);
        }
        int i = getI(population);
        return  population.get(i).get(0)+ " " + population.get(i).get(1) + " " + population.get(i).get(2) + " " + population.get(i).get(3)+ " Кл-во мутаций " + m;
    }


    int getY(ArrayList<Integer> i) {
        return a * i.get(0) + b * i.get(1) + c * i.get(2) + d * i.get(3);
    }

    ArrayList<ArrayList<Integer>> getM(ArrayList<ArrayList<Integer>> a) {
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            a.add(new ArrayList<Integer>());
            for (int j = 0; j < 4; j++) {
                a.get(i).add(r.nextInt(y / 2) + 1);
            }
        }
        return a;
    }

    int getI(ArrayList<ArrayList<Integer>> a) {
        int min = Integer.MAX_VALUE;
        int i = -1;
        for (int j = 0; j < a.size(); j++) {
            int temp2 = getY(a.get(j));
            if (temp2 - y < min) {
                min = temp2;
                i = j;
            }
        }
        return i;
    }

    ArrayList<ArrayList<Integer>> getChild(ArrayList<ArrayList<Integer>> a,Integer[][] child){
     for (int i = 0; i < child.length; i++) {
        a.add(new ArrayList<Integer>());
        for (int j = 0; j < child.length; j++) {
            a.get(i).add(child[i][j]);
        }
    }
     return a;
}

    Double[] getRange(Double[] range,Integer[] values, Double p) {
        for (int i = 0; i < range.length - 1; i++) {
            range[i + 1] = range[i] + (values[i] / p);
        }
        return range;
    }

    Integer[] getParent(Integer[] parentsid,Double[] range) {
        Random r = new Random();
        for (int i = 0; i < parentsid.length; i++) {
            int temp1 = r.nextInt(100);
            int id = 1;
            while (id < range.length && range[id] < temp1) {
                id++;
            }
            parentsid[i] = (id == 0) ? id : id - 1;
        }
        return parentsid;
    }
    Integer[][] getC(ArrayList<ArrayList<Integer>> a,Integer p1,Integer p2,Integer[][] child, int threshold,int i) {
        for (int j = 0; j < child.length; j++) {
            child[i][j] = (j < threshold) ? a.get(p1).get(j) : a.get(p2).get(j);
        }
        return child;
    }

    Integer[][] getMut(Random r,Double mutation,Integer[][] child, int i) {
        if (r.nextDouble() < mutation) {
            int choice = r.nextInt(child.length);
            child[i][choice] += (r.nextDouble() > 0.5 && child[i][choice] < y / 2) ? 1 : (child[i][choice] > 1) ? -1 : 0;
            m++;
        }
        return child;
    }
    Integer[][] getR(ArrayList<ArrayList<Integer>> a,Random r,Double mutation,Integer[][] child, Integer[] parentsid) {
        for (int i = 0; i < a.size(); i++) {
            Integer p1 = parentsid[r.nextInt(parentsid.length - 1)];
            Integer p2 = parentsid[r.nextInt(parentsid.length - 1)];
            int threshold = r.nextInt(3) + 1;

            getC(a, p1, p2, child, threshold, i);
            getMut(r, mutation, child, i);
        }
        return child;
    }

}
