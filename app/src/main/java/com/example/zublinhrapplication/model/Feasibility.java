package com.example.zublinhrapplication.model;

public enum Feasibility {
    VERY_EASY(0),
    EASY(1),
    NORMAL(2),
    HARD(3),
    VERY_HARD(4);

    private final int level;

    Feasibility(int level){
        this.level = level;
    }
}
