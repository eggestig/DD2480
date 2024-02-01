package com.mycompany.app;

import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.Arrays;

public class CMV {

    public static LICutils utils = new LICutils();

    public static boolean evalLIC(int LIC_num, Parameters param) {
        // TODO: LIC 1 - 14

        switch(LIC_num) {
            case(0):
                return LIC0(param);
            // case(5):
            //     return LIC5(param);
            // case(6):
            //     return LIC6(param);
            // case(7):
            //     return LIC7(param);
            // case(8):
            //     return LIC8(param);
            default:
                return false;
            }
    }

    public static boolean LIC0(Parameters param) {
        // TODO:
        // Return true if there exists at least one set of two consecutive data points
        // that are a distance greater than the length, LENGTH1, apart.
        // Else return false.

        return false;
    }

    public static Boolean LIC5(Parameters param) {
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

    public static Boolean LIC6(Parameters param) {
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
        
        if(x_pts.length < 3 || y_pts.length < 3) {
            return false;
        }
        
        if(n_pts < 3 || n_pts > numPoints || dist < 0) {
            return false;
        }

        for(int i = 0; i < numPoints; i++) {
            if(i + n_pts - 1 >= numPoints) {
                return false;
            }
            for(int j = i + 1; j < i + n_pts - 1; j++) {
                double x1 = x_pts[i];
                double y1 = y_pts[i];
                double x2 = x_pts[j];
                double y2 = y_pts[j];
                double x3 = x_pts[i + n_pts - 1];
                double y3 = y_pts[i + n_pts - 1];
                if(x1 == x3 && y1 == y3) {
                    if(utils.dist(x1, y1, x2, y2) > dist) {
                        return true;
                    }
                } else if(utils.distToLine(x1, y1, x2, y2, x3, y3) > dist) {
                    return true;
                }
            }
        }

        return  false;
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

    public static Boolean LIC7(Parameters param) {
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

        if(k_pts < 1 || k_pts > numPoints - 2 || length1 < 0) {
            return false;
        }

        if(x_pts.length < 3 || y_pts.length < 3) {
            return false;
        }
        

        System.out.flush();
        for(int i = 0; i < numPoints - k_pts - 1; i++) {
            if(utils.dist(x_pts[i], y_pts[i], x_pts[i + k_pts + 1], y_pts[i + k_pts + 1]) > length1) {

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

    public static Boolean LIC8(Parameters param) {
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
            return false;
        }

        if(radius1 < 0) {
            return false;
        }

        if(x_pts.length < 5 || y_pts.length < 5) {
            return false;
        }

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

    public static Boolean LIC11(Parameters param) {
        // Return true if there exists at least one set of two data points,
        // (X[i],Y[i]) and (X[j],Y[j]), separated by exactly G_PTS
        // consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
        // Else return false.
        
        // The condition is not met when NUMPOINTS < 3.
        // 1 ≤ G_PTS ≤ NUMPOINTS−2

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