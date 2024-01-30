package de.mkammerer.skeleton;
import java.util.Random;

public class Fuv {
    public static void main(String[] args) throws Exception {

        int i;
        int j;

        // Generate random PUM - preliminary unlocking matrix
        Random rand = new Random();
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