package com.service.weather.models;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Wind {
    private double speed;
    private int deg;
    private double gust;

    public double getSpeed() {
        return speed*3.6;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }
}
