package com.mycompany.app;
import java.util.Random;

public class App {
    static Parameters param;
    static Boolean[] cmv = new Boolean[15];
    static int NUMPOINTS;
    static int[] x_pts;
    static int[] y_pts;
    
    public static void main(String[] args) throws Exception {

        /* Create default "global" variables */
        Random rand = new Random();

        NUMPOINTS = rand.nextInt(100 - 2) + 2;
        int p_pts = rand.nextInt(NUMPOINTS - 2) + 2;
        x_pts = new int[NUMPOINTS];
        y_pts = new int[NUMPOINTS];
        param = new Parameters
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