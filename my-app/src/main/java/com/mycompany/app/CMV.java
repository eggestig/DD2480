package com.mycompany.app;

import java.util.HashSet;
import java.util.Arrays;

public class CMV{

    private static Parameters param;
    private static int numPoints;
    private static double[] x_pts;
    private static double[] y_pts;
    private static LICutils utils = new LICutils();

    private static boolean evalLIC(int LIC_num) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0(param);
            case(12):
                return LIC12(param);
            case(13):
                return LIC13(param);
            case(14):
                return LIC14(param);
        
            default:
                return false;
            }
    }

    private static boolean LIC0(Parameters param) {
        // TODO:
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        return false;
    }
    //assume all perivous is true

    public static boolean LIC12(Parameters param) {
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

        int k_pts = param.getK_PTS();
        double length2 = param.getLENGTH2();
        
        if(x_pts.length < 3 || y_pts.length < 3 || Boolean.TRUE.equals(/*!LIC7()*/false)) {return false;}
        
        for(int i = 0; i < numPoints - (k_pts + 1); i++) {
            
            if(LICutils.dist( x_pts[i],  y_pts[i],  x_pts[i + k_pts +1],  y_pts[i + k_pts +1]) < length2) {
                return true;
            }
            
        }

        return false;
    }
    public static Boolean LIC13(Parameters param) {
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

        for(int i = 0; i < numPoints - ( a_pts + b_pts+2); i++) {
 
            double x1 = x_pts[i]; double y1 = y_pts[i];
            double x2 = x_pts[i+a_pts+1]; double y2 = y_pts[i+a_pts+1];
            double x3 = x_pts[i+a_pts+b_pts+2]; double y3 = y_pts[i+a_pts+b_pts+2];
    
            double a = LICutils.dist(x1, y1, x2, y2);
            double b = LICutils.dist(x2, y2, x3, y3);
            double c = LICutils.dist(x3, y3, x1, y1);
    
            if(LICutils.circumRadius(a, b, c) <= param.getRADIUS2()) {
                return true;

            }
       }

        return false;
    }

    public static Boolean LIC14(Parameters param) {
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
    
        if(x_pts.length < 5 || y_pts.length < 5 || Boolean.TRUE.equals(/*!LIC10()*/false)) {return false;}

        int e_pts= param.getE_PTS();
        int f_pts= param.getF_PTS();
        
        for(int i = 0; i < numPoints - (e_pts + f_pts +2); i++) {
            
            double x1 =  x_pts[i];
            double y1 =  y_pts[i];
            double x2 =  x_pts[i+e_pts+1];
            double y2 =  y_pts[i+e_pts+1];
            double x3 =  x_pts[i+e_pts+f_pts+2];
            double y3 =  y_pts[i+e_pts+f_pts+2];

            if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) < param.getAREA2()) {
                return true;
            }

        }
        return false;
    }

    public static boolean LIC9(Parameters params) {
        // Return true if there exists at least one set of three data points separated by
        // exactly C_PTS and D_PTS consecutive intervening points, respectively,
        // that form an angle such that:
        // angle < (PI − EPSILON) or angle > (PI + EPSILON)
        // Else return false.

        // The second point of the set of three points is always the vertex of the angle.
        // If either the first point or the last point (or both) coincide with the vertex,
        // the angle is undefined and the LIC is not satisfied by those three points. 
        // When NUMPOINTS < 5, the condition is not met.
        // 1 ≤ C_PTS, 1 ≤ D_PTS
        // C_PTS + D_PTS ≤ NUMPOINTS−3

        //TODO: Throw errors here if invalid input

        for(int i = 0; i < params.getNUMPOINTS() - (params.getC_PTS() + params.getD_PTS() + 2); i++) {
            int j = i + 1 + params.getC_PTS();
            int k = j + 1 + params.getD_PTS();
            
            double x1 = params.getX_PTS()[i];
            double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[j]; // Vertex
            double y2 = params.getY_PTS()[j]; // Vertex
            double x3 = params.getX_PTS()[k];
            double y3 = params.getY_PTS()[k];

            double n1 = utils.euclideanNorm(x1, y1, x2, y2);
            double n2 = utils.euclideanNorm(x2, y2, x3, y3);

            // Calculate the cosine of the angle between the vectors
            double cosineAngle = utils.dotProduct(x1, y1, x2, y2, x3, y3) / (Math.abs(n1) * Math.abs(n2));

            // Calculate the angle in radians
            double angle = Math.acos(cosineAngle);
    
            if(angle < (Math.PI - params.getEPSILON()) || angle > (Math.PI + params.getEPSILON())) {
                //System.out.println(angle  + "<" + (Math.PI - params.getEPSILON()) + " || " + angle + ">" + (Math.PI + params.getEPSILON()));
                //System.out.flush(); 
                return true;
            }
        }

        return false;
    }

    public static Boolean LIC10(Parameters param) {
        // Return true if there exists at least one set of three data points
        // separated by exactly E_PTS and F_PTS consecutive intervening points,
        // respectively, that are the vertices of a triangle with area greater than AREA1.
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 5.
        // 1 ≤ E_PTS, 1 ≤ F_PTS
        // E_PTS + F_PTS ≤ NUMPOINTS − 3


        //TODO: Throw errors here if invalid input
        if(param.getX_PTS().length < 5 || param.getY_PTS().length < 5) {return false;}

        for(int i = 0; i < param.getNUMPOINTS() + (param.getE_PTS() + param.getF_PTS()); i++) {
            for(int j = i + param.getE_PTS(); j < param.getNUMPOINTS() - param.getF_PTS(); j++) {
                for(int k = j + param.getF_PTS(); k < param.getNUMPOINTS(); k++) {
                
                    double x1 = param.getX_PTS()[i];
                    double y1 = param.getY_PTS()[i];
                    double x2 = param.getX_PTS()[j]; // Vertex
                    double y2 = param.getY_PTS()[j]; // Vertex
                    double x3 = param.getX_PTS()[k];
                    double y3 = param.getY_PTS()[k];

                    if(0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) > param.getAREA1()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static Boolean LIC11(Parameters param) {
        // Return true if there exists at least one set of two data points,
        // (X[i],Y[i]) and (X[j],Y[j]), separated by exactly G_PTS
        // consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ G_PTS ≤ NUMPOINTS−2

        //TODO: Throw errors here if invalid input

        if(param.getX_PTS().length < 3 || param.getY_PTS().length < 3) {return false;}

        for(int i = 0; i < param.getNUMPOINTS() - param.getG_PTS(); i++) {
            for(int j = i + param.getG_PTS(); j < param.getNUMPOINTS(); j++) {
                if(param.getX_PTS()[j] - param.getX_PTS()[i] < 0) {
                    return true;
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