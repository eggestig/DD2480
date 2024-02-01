package com.mycompany.app;

import java.util.HashSet;

public class CMV{

    private static int numPoints;
    public static Parameters param;
    private static double[] x_pts;
    private static double[] y_pts;
    private static LICutils utils = new LICutils();

    private static boolean evalLIC(int LIC_num) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0();
            case(12):
                return LIC12();
            case(13):
                return LIC13();
            case(14):
                return LIC14();
        
            default:
                return false;
            }
    }

    private static boolean LIC0() {
        // TODO:
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        return false;
    }
    //assume all perivous is true
    private static boolean LIC7() { return true; }
    private static boolean LIC8() { return true; }
    private static boolean LIC10() { return true; }

    public static boolean LIC12() {
        // Return true if there exists at least one set of two data points,
        // separated by exactly K_PTS consecutive intervening points,
        // which are a distance greater than the length, LENGTH1, apart.
        // In addition, there exists at least one set of two data points 
        // (which can be the same or different from the two data points just mentioned),
        // separated by exactly K_PTS consecutive intervening points,
        // that are a distance less than the length, LENGTH2, apart. 
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 3.
        // 0 ≤ LENGTH2

        x_pts = param.getX_PTS();
        y_pts = param.getY_PTS();

        int k_pts = param.getK_PTS();
        double length2 = param.getLENGTH2();
        
        if(x_pts.length < 3 || y_pts.length < 3 || Boolean.TRUE.equals(/*!LIC7()*/false)) {return false;}
        
        for(int i = 0; i < param.getNUMPOINTS() - k_pts; i++) {
            
            if(LICutils.dist( x_pts[i],  y_pts[i],  x_pts[i + k_pts],  y_pts[i + k_pts]) < length2) {
                return true;
            }
            
        }

        return false;
    }
    private static Boolean LIC13() {
        // Return true if there exists at least one set of three data points,
        // separated by exactly A_PTS and B_PTS consecutive intervening points,
        // respectively, that cannot be contained within or on a circle of radius RADIUS1.
        // In addition, there exists at least one set of three data points
        // (which can be the same or different from the three data points just mentioned)
        // separated by exactly A_PTS and B_PTS consecutive intervening points, respectively,
        // that can be contained in or on a circle of radius RADIUS2. 
        
        // The condition is not met when NUMPOINTS < 5.
        // 0 ≤ RADIUS2

        if(x_pts.length < 5 || y_pts.length < 5 || Boolean.TRUE.equals(/*!LIC8()*/false)) {return false;}

        int a_pts= param.getA_PTS();
        int b_pts= param.getB_PTS();

        for(int i = 0; i < numPoints - ( a_pts + b_pts); i++) {
            for(int j = i + b_pts; j < numPoints - b_pts; j++) {
                for(int k = j + b_pts; k < numPoints; k++) {

                    double x1 = x_pts[i]; double y1 = y_pts[i];
                    double x2 = x_pts[j]; double y2 = y_pts[j];
                    double x3 = x_pts[k]; double y3 = y_pts[k];
            
                    Double a = LICutils.dist(x1, y1, x2, y2);
                    Double b = LICutils.dist(x2, y2, x3, y3);
                    Double c = LICutils.dist(x3, y3, x1, y1);
            
                    if(LICutils.circumRadius(a, b, c) <= param.getRADIUS2()) {
                        return true;
                    }
                }
            }
       }

        return false;
    }

    private static Boolean LIC14() {
        // Return true if there exists at least one set of three data points,
        // separated by exactly E_PTS and F_PTS consecutive intervening points,
        // respectively, that are the vertices of a triangle with area greater than AREA1.
        // In addition, there exist three data points
        // (which can be the same or different from the three data points just mentioned)
        // separated by exactly E_PTS and F_PTS consecutive intervening points, respectively,
        // that are the vertices of a triangle with area less than AREA2.
        // Both parts must be true for the LIC to be true.
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 5.
        // 0 ≤ AREA2
    
        if(x_pts.length < 5 || y_pts.length < 5 || Boolean.TRUE.equals(!LIC10())) {return false;}

        int e_pts= param.getE_PTS();
        int f_pts= param.getF_PTS();
        
        for(int i = 0; i < numPoints + (e_pts + f_pts); i++) {
            for(int j = i + e_pts; j < numPoints - f_pts; j++) {
                for(int k = j + f_pts; k < numPoints; k++) {
                
                    double x1 =  x_pts[i];
                    double y1 =  y_pts[i];
                    double x2 =  x_pts[j];
                    double y2 =  y_pts[j];
                    double x3 =  x_pts[k];
                    double y3 =  y_pts[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) < param.getAREA2()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean[] initCMV(Parameters PARAM, int NUMPOINTS, double[] xpts, double[] ypts) {

        numPoints = NUMPOINTS;
        param = PARAM;
        x_pts = xpts;
        y_pts = ypts;
        boolean[] cmv = new boolean[15];

        for(int i = 0; i < 15; i++) {
            cmv[i] = evalLIC(i);
        }

        return cmv;
    }
}