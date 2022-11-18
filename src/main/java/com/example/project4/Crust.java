package com.example.project4;

public enum Crust {
    DEEP_DISH, PAN, STUFFED, // Chicago style
    BROOKLYN, THIN, HAND_TOSSED; // NY style

    @Override
    public String toString(){
        return (this.name().charAt(0) + this.name().substring(1).toLowerCase()).replace("_", " ");
    }
}
