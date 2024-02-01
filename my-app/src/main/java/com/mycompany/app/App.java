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

        /* Create default "global" variables */
        // Random rand = new Random();
        // int numpoints = rand.nextInt(100 - 2) + 2;
        // double[] x_pts = new double[numpoints];
        // double[] y_pts = new double[numpoints];

        InputParser inputparser = new InputParser(args[0]);
        inputparser.Parse();

        System.out.println("NUMBERS: " + inputparser.getParameters().getNUMPOINTS());

        param = inputparser.getParameters();
        lcm = inputparser.getLCM();
        puv = inputparser.getPUV();
    

        // Fill arrays
        // Arrays.fill(x_pts, 0);
        // Arrays.fill(y_pts, 1);

        boolean[] cmv = CMV.compute(param);

        // CONNECTORS[][] sampleLCM = {
        //     {CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ORR},
        //     {CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR},
        //     {CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR},
        //     {CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ORR},
        //     {CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.ORR, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.ORR},
        //     {CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ANDD, CONNECTORS.NOTUSED, CONNECTORS.NOTUSED, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.NOTUSED, CONNECTORS.ORR},
        //     {CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.ORR, CONNECTORS.NOTUSED}
        //  };


        PUM pum = new PUM();
        pum.receive_CMV(cmv);
        pum.receive_LCM(lcm);
        pum.generate_PUM();

        pum.displayPUM();
        
        // String[][] stringPUM = pum.getStringPUM();
        boolean[][] booleanPUM = pum.getPUM();
   


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
        // int[][] pum = new int[len][len];
        // for (i = 0; i < len; i++) {
        //     for  (j = 0; j < len; j++) {
        //         pum[i][j] = rand.nextInt(2);
        //     }
        // }

        //Generate PUV - preliminary unlocking vector
        // int[] puv = new int[len];
        // for (i = 0; i < len; i++) {
        //     puv[i] = rand.nextInt(2);
        //     System.out.print(" " + puv[i]);
        // }

        // Extract FUV - final unlocking vector
        // System.out.println("FUV: ");
        int[] fuv = new int[len];
        for (i = 0; i < len; i++) {

            // Condition 1
            if (puv[i]==false) {
                fuv[i] = 1;
                //System.out.print(fuv[i] + (" "));
                continue;
            } 

            // Condition 2

            int unlock;
            for (i = 0; i < len; i++) {
                unlock = 0;

                for (j = 0; j < len; j++) {
                    if (i==j) {
                        continue;
                    }
                    if (booleanPUM[i][j]==true) {
                        unlock = unlock + 1;
                    }
                }
                
                if (unlock==15) {
                    fuv[i] = 1;
                }

               // System.out.print(fuv[i] + (" "));
            }  
        }

        System.out.println("Do we want to launch the missile?");
        for (i = 0; i < len; i++) {
            if (fuv[i]==0) {
                System.out.println("");
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}