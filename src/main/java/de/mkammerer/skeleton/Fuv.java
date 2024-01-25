package de.mkammerer.skeleton;
import java.util.Random;

public class Fuv {
    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        int len = 50;
        int[][] data = new int[2][len];
        for (int i = 0; i < 5; i++) {
            data[0][i] = rand.nextInt(100);
            data[1][i] = rand.nextInt(100);
        }
          
        System.out.println("Hello, World!");
    }
}