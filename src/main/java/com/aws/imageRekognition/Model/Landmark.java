package com.aws.imageRekognition.Model;

public class Landmark {

    private String Type;

    private double X;

    private double Y;

    public Landmark(String type, double x, double y) {
        Type = type;
        X = x;
        Y = y;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }
}
