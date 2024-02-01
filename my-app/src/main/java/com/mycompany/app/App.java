package com.mycompany.app;
import java.util.Random;
import java.util.Arrays; 

public class App {
    static Parameters param;
    static boolean[] cmv;
    static int NUMPOINTS;
    static double[] x_pts;
    static double[] y_pts;
    
    public static void main(String[] args) throws Exception {

        /* Create default "global" variables */
        Random rand = new Random();
        int numpoints = rand.nextInt(100 - 2) + 2;
        double[] x_pts = new double[numpoints];
        double[] y_pts = new double[numpoints];

        // Fill arrays
        Arrays.fill(x_pts, 0);
        Arrays.fill(y_pts, 1);

        Parameters param = new Parameters(
            numpoints,
            x_pts,
            y_pts,
            1.0,
            2.0,
            3.0,
            4.0,
            5,
            6,
            7.0,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17.0,
            18.0,
            19.0
        );

        boolean[] cmv = CMV.initCMV(param);

        int k = 0;
        for(boolean lic : cmv) {
            System.out.println("LIC" + k + '\t' + lic);
            k++;
        }
        
        System.out.println("Parameters created");



        int i;
        int j;

        // Generate random PUM - preliminary unlocking matrix
        int len = 15;
        int[][] pum = new int[len][len];
        for (i = 0; i < len; i++) {
            for  (j = 0; j < len; j++) {
                pum[i][j] = rand.nextInt(2);
            }
        }

        // Generate PUV - preliminary unlocking vector
        int[] puv = new int[len];
        for (i = 0; i < len; i++) {
            puv[i] = rand.nextInt(1);
        }

        // Extract FUV - final unlocking vector
        // System.out.println("FUV: ");
        int[] fuv = new int[len];
        for (i = 0; i < len; i++) {

            // Condition 1
            if (puv[i]==0) {
                fuv[i] = 1;
                //System.out.print(fuv[i] + (" "));
                continue;
            } 

            // Condition 2

            int unlock;
            for (i = 0; i < len; i++) {
                unlock = 0;

                for (j = 0; j < len; j++) {
                    if (pum[i][j]==1) {
                        unlock = unlock + 1;
                    }
                }
                
                if (unlock==15) {
                    fuv[i] = 1;
                }

               // System.out.print(fuv[i] + (" "));
            }  
        }
    }
}