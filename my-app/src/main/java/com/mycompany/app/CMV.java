package com.mycompany.app;

import java.lang.reflect.Parameter;
import java.security.InvalidParameterException;
import java.util.HashSet;

public class CMV {

    private static LICutils utils = new LICutils();

    private static boolean evalLIC(int LIC_num, Parameters param) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0(param);
            case(5):
                return LIC5(param);
            case(6):
                return LIC6(param);
            case(7):
                return LIC7(param);
            case(8):
                return LIC8(param);
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

    private static Boolean LIC5(Parameters param) {
        // Return true if there exists at least one set of two consecutive data points,
        // (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0
        // (where i = j-1).
        // Else return false.
        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();

        for(int i = 0; i < numPoints; i++) {
            for(int j = i + 1; j < numPoints; j++) {
                if(x_pts[j] - x_pts[i] < 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Boolean LIC6(Parameters param) {
        // Return true if there exists at least one set of N_PTS consecutive data points
        // such that at least one of the points lies a distance greater than DIST from
        // the line joining the first and last of these N_PTS points.
        // Else return false.

        // If the first and last points of these N_PTS are identical,
        // then the calculated distance to compare with DIST will be the distance from the
        // coincident point to all other points of the N_PTS consecutive points.
        // The condition is not met when NUMPOINTS < 3.
        // (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)

        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        double dist = param.getDIST();
        int n_pts = param.getN_PTS();

        if(n_pts < 3 || n_pts > numPoints) {
            throw new InvalidParameterException("N_PTS need to be between 3 and NUMPOINTS inclusive");
        }

        if(dist < 0) {
            throw new InvalidParameterException("dist must be positive float value");
        }

        if(x_pts.length < 3 || y_pts.length < 3) {return false;}

        for(int i = 0; i < numPoints - n_pts; i++) {
            for(int j = i + 1; j < n_pts; j++) {
                if(utils.distToLine(x_pts[j], y_pts[j], x_pts[i], y_pts[i], x_pts[i + n_pts], y_pts[i + n_pts]) > dist) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Boolean LIC7(Parameters param) {
        // Return true if there exists at least one set of two data points separated by
        // exactly K_PTS consecutive intervening points that are a distance greater than
        // the length, LENGTH1, apart.
        // Else return false.

        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ K_PTS ≤ (NUMPOINTS − 2)

        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        int k_pts = param.getK_PTS();
        double length1 = param.getLENGTH1();

        if(k_pts < 1 || k_pts > numPoints - 2) {
            throw new InvalidParameterException("K_PTS must be between 1 and NUMPOINTS - 2 inclusive");
        }

        if(length1 < 0) {
            throw new InvalidParameterException("LENGTH1 must be positive float value");
        }

        if(x_pts.length < 3 || y_pts.length < 3) {return false;}
        
        for(int i = 0; i < numPoints - k_pts - 1; i++) {
            if(utils.dist(x_pts[i], y_pts[i], x_pts[i + k_pts + 1], y_pts[i + k_pts + 1]) > length1) {
                return true;
            }
        }

        return false;
    }

    private static Boolean LIC8(Parameters param) {
        // Return true if there exists at least one set of three data points
        // separated by exactly A_PTS and B_PTS consecutive intervening points,
        // respectively, that cannot be contained within or on a circle of radius RADIUS1.
        // Else return false.

        // The condition is not met when NUMPOINTS < 5.
        // 1 ≤ A_PTS, 1 ≤ B_PTS
        // A_PTS + B_PTS ≤ (NUMPOINTS−3)

        int numPoints = param.getNUMPOINTS();
        double[] x_pts = param.getX_PTS();
        double[] y_pts = param.getY_PTS();
        int a_pts = param.getA_PTS();
        int b_pts = param.getB_PTS();
        double radius1 = param.getRADIUS1();

        if(a_pts < 1 || b_pts < 1 || (a_pts + b_pts) > numPoints - 3) {
            throw new InvalidParameterException("A_PTS and/or B_TS invalid integer value");
        }

        if(radius1 < 0) {
            throw new InvalidParameterException("RADIUS1 must be positive float value");
        }

        if(x_pts.length < 5 || y_pts.length < 5) {return false;}

        for(int i = 0; i < numPoints - (a_pts + b_pts); i++) {
            for(int j = i + a_pts; j < numPoints - b_pts; j++) {
                for(int k = j + b_pts; k < numPoints; k++) {

                    double x1 = x_pts[i]; double y1 = y_pts[i];
                    double x2 = x_pts[j]; double y2 = y_pts[j];
                    double x3 = x_pts[k]; double y3 = y_pts[k];
            
                    Double a = utils.dist(x1, y1, x2, y2);
                    Double b = utils.dist(x2, y2, x3, y3);
                    Double c = utils.dist(x3, y3, x1, y1);
            
                    if(utils.circumRadius(a, b, c) > radius1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public CMV() {
        
    }

    public static boolean[] initCMV(Parameters param) {

        boolean[] cmv = new boolean[15];

        for(int i = 0; i < 15; i++) {
            cmv[i] = evalLIC(i, param);
        }

        return cmv;
    }
}