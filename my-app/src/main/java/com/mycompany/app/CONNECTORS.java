package com.mycompany.app;

public enum CONNECTORS {
    NOTUSED(0),
    ORR(1),
    ANDD(2); 

    private final int value;

    CONNECTORS(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
