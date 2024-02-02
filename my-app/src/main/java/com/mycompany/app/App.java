package com.mycompany.app;
import java.util.Random;
import java.util.Arrays; 

public class App {
    static Parameters param;
    static boolean[] cmv;
    static CONNECTORS[][] lcm;
    static boolean[] puv;
    static int NUMPOINTS;
    static double[] x_pts;
    static double[] y_pts;
    
    public static void main(String[] args) throws Exception {

        Decide decide = new Decide(args[0]);
        decide.decide();
    }
}