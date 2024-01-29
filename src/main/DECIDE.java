package main;


import java.util.Random;
import de.mkammerer.skeleton.*;

class DECIDE {
    static Parameters param;
    static Boolean[] cmv = new Boolean[15];
    static int NUMPOINTS;
    static int[] x_pts;
    static int[] y_pts;


    public static void main(String[] args) {

        Random rand = new Random();

        NUMPOINTS = rand.nextInt(100 - 2) + 2;
        int p_pts = rand.nextInt(NUMPOINTS - 2) + 2;
        x_pts = new int[NUMPOINTS];
        y_pts = new int[NUMPOINTS];
        Parameters param = new Parameters
                            (
                            100.0,
                            10.0,
                            (double) rand.nextInt(3),
                            1000.0,
                            rand.nextInt(NUMPOINTS), 
                            rand.nextInt(10),
                            100.0,
                            p_pts,
                            p_pts,
                            p_pts,
                            p_pts,
                            p_pts,
                            p_pts,
                            p_pts,
                            p_pts,
                            p_pts,
                            100.0,
                            50.0,
                            100.0
                            );

        for(int i = 0; i < NUMPOINTS; i++) {
            x_pts[i] = i;
            y_pts[i] = i;
        }

        cmv = initCMV(param, NUMPOINTS, x_pts, y_pts);

        int i = 0;
        for(Boolean lic : cmv) {
            System.out.println("LIC" + i + '\t' + lic);
            i++;
        }
    }
}