package com.aws.imageRekognition.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Box {
    private double Width;
    private double Height;
    private double Left;
    private double Top;

    public Box(double width, double height, double left, double top) {
        Width = width;
        Height = height;
        Left = left;
        Top = top;
    }

    public double getWidth() {
        return Width;
    }

    @JsonProperty("Width")
    public void setWidth(double width) {
        Width = width;
    }

    public double getHeight() {
        return Height;
    }

    @JsonProperty("Height")
    public void setHeight(double height) {
        Height = height;
    }

    public double getLeft() {
        return Left;
    }

    @JsonProperty("Left")
    public void setLeft(double left) {
        Left = left;
    }

    public double getTop() {
        return Top;
    }

    @JsonProperty("Top")
    public void setTop(double top) {
        Top = top;
    }
}