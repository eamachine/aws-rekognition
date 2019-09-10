package com.aws.imageRekognition.Model;

public class ModerationLabel {

    private String ParentName;

    private String Name;

    private double Confidence;

    public ModerationLabel(String parentName, String name, double confidence) {
        ParentName = parentName;
        Name = name;
        Confidence = confidence;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getConfidence() {
        return Confidence;
    }

    public void setConfidence(double confidence) {
        Confidence = confidence;
    }
}
