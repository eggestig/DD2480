package com.mycompany.app;

public class Decide {
    private Parameters param;
    private boolean[] cmv;
    private CONNECTORS[][] lcm;
    private boolean[] puv;
    private int NUMPOINTS;
    private double[] x_pts;
    private double[] y_pts;
    private String path; 
    
    public Decide(String path) {
        this.path = path;
    }
    
    public String decide() {
    

        InputParser inputparser = new InputParser(path);
        inputparser.Parse();

        System.out.println("NUMBERS: " + inputparser.getParameters().getNUMPOINTS());

        param = inputparser.getParameters();
        lcm = inputparser.getLCM();
        puv = inputparser.getPUV();
    

        boolean[] cmv = CMV.compute(param);


        PUM pum = new PUM();
        pum.receive_CMV(cmv);
        pum.receive_LCM(lcm);
        pum.generate_PUM();

        pum.displayPUM();
        boolean[][] booleanPUM = pum.getPUM();
   
        int k = 0;
        for(boolean lic : cmv) {
            System.out.println("LIC" + k + '\t' + lic);
            k++;
        }
        
        System.out.println("Parameters created");

        int i;
        int j;


        int len = 15;
        int[] fuv = new int[len];
        for (i = 0; i < len; i++) {

            // Condition 1
            if (puv[i]==false) {
                fuv[i] = 1;
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
                
                if (unlock==14) {
                    fuv[i] = 1;
                }

            }  
        }

        System.out.println("Do we want to launch the missile?");
        for (i = 0; i < len; i++) {
            if (fuv[i]==0) {
                System.out.println("");
                System.out.println("NO");
                return "NO";
            }
        }
        System.out.println("YES");
        return "YES";
    }
}