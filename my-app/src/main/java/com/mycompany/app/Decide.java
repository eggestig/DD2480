package com.mycompany.app;

public class Decide {
    private Parameters param;
    private CONNECTORS[][] lcm;
    private boolean[] puv;
    private String path; 
    
    public Decide(String path) {
        this.path = path;
    }
    
    public String decide() {
    

        InputParser inputparser = new InputParser(path);
        inputparser.Parse();

        param = inputparser.getParameters();
        lcm = inputparser.getLCM();
        puv = inputparser.getPUV();
    
        boolean[] cmv = CMV.compute(param);

        PUM pum = new PUM();
        pum.receive_CMV(cmv);
        pum.receive_LCM(lcm);
        pum.generate_PUM();

        boolean[][] booleanPUM = pum.getPUM();

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

        for (i = 0; i < len; i++) {
            if (fuv[i]==0) {
                return "NO";
            }
        }
        return "YES";
    }
}