package com.aws.imageRekognition.Model;

public class Emotion {

    private String Type;

    private double Confidence;

    public Emotion(String type, double confidence) {
        Type = type;
        Confidence = confidence;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getConfidence() {
        return Confidence;
    }

    public void setConfidence(double confidence) {
        Confidence = confidence;
    }
}
