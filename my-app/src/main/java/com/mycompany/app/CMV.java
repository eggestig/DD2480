package com.mycompany.app;

import java.util.HashSet;
import java.util.Arrays;

public class CMV {

    private static int numPoints;
    private static Parameters param;
    private static double[] x_pts;
    private static double[] y_pts;
    private static LICutils utils = new LICutils();

    private static boolean evalLIC(int LIC_num) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0(param);
            case(1):
                return LIC1(param);
            case(2):
                return LIC2(param);
            default:
                return false;
            }
    }

    public static boolean LIC0(Parameters params) {
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        if (params.getX_PTS().length < 2 || params.getY_PTS().length  < 2) {
            return false;
        }

        if (params.getLENGTH1() < 0)
            return false;

        for(int i = 0; i < params.getNUMPOINTS() - 1; i++) {
            if(utils.dist(params.getX_PTS()[i], params.getY_PTS()[i], params.getX_PTS()[i+1], params.getY_PTS()[i+1]) > params.getLENGTH1()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean LIC1(Parameters params){
        // Return true if there exists at least one set of three consecutive data points that cannot all be contained
        // within or on a circle of radius RADIUS1. (0 ≤ RADIUS1)
        // Else return false.

        if (params.getX_PTS().length < 3 || params.getY_PTS().length  < 3 ) {
            return false;
        }

        for(int i = 0; i < params.getNUMPOINTS() - 2; i++) {

            double x1 = params.getX_PTS()[i]; double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[i+1]; double y2 = params.getY_PTS()[i+1];
            double x3 = params.getX_PTS()[i+2]; double y3 = params.getY_PTS()[i+2];
    
            double a = utils.dist(x1, y1, x2, y2);
            double b = utils.dist(x2, y2, x3, y3);
            double c = utils.dist(x3, y3, x1, y1);

            if(utils.circumRadius(a, b, c) > params.getRADIUS1()) {
                return true;
            }
        }

        return false;
    }

    public static Boolean LIC2(Parameters params) {
        // Return true if there exists at least one set of three consecutive data points
        // which form an angle such that: angle < (PI − EPSILON) or angle > (PI + EPSILON).
        // Return false if either the first point or the last point (or both) coincides with the vertex.
        // (0 ≤ EPSILON < PI)

        // The second of the three consecutive points is always the vertex of the angle.
        // If either the first point or the last point (or both) coincides with the vertex,
        // the angle is undefined and the LIC is not satisfied by those three points.

        if (params.getX_PTS().length < 3 || params.getY_PTS().length  < 3 ) {
            return false;
        }

        if (params.getEPSILON() < 0 || params.getEPSILON() > Math.PI) {
            return false;            
        }

        for(int i = 0; i < params.getNUMPOINTS() - 2; i++) {
                    
            double x1 = params.getX_PTS()[i];
            double y1 = params.getY_PTS()[i];
            double x2 = params.getX_PTS()[i+1]; // Vertex
            double y2 = params.getY_PTS()[i+1]; // Vertex
            double x3 = params.getX_PTS()[i+2];
            double y3 = params.getY_PTS()[i+2];
    
            double n1 = utils.euclideanNorm(x1, y1, x2, y2);
            double n2 = utils.euclideanNorm(x2, y2, x3, y3);
    
            // Calculate the cosine of the angle between the vectors
            double cosineAngle = utils.dotProduct(x1, y1, x2, y2, x3, y3) / (n1 * n2);
    
            // Calculate the angle in radians
            double angle = Math.acos(cosineAngle);
    
            if(angle < (Math.PI - params.getEPSILON()) || angle > (Math.PI + params.getEPSILON())) {
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