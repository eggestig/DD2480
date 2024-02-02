package com.mycompany.app;

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
        System.out.println(decide.decide());
    }
}