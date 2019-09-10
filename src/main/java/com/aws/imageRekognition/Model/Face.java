package com.aws.imageRekognition.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Face {

    private int Age;

    private String Gender;

    private String CelebrityName;

    private double CelebrityScore;

    private Box Box;

    private List<Emotion> Emotions;

    private double Confidence;

    private List<Landmark> landmarks;

    public Face(double confidence, Box box, int age, String gender, String celebrityName, double celebrityScore, List<Landmark> landmarks, List<Emotion> emotions) {
        Confidence = confidence;
        Box = box;
        this.Age = age;
        Gender = gender;
        CelebrityName = celebrityName;
        CelebrityScore = celebrityScore;
        this.landmarks = landmarks;
        this.Emotions = emotions;
    }

    public double getConfidence() {
        return Confidence;
    }

    public void setConfidence(double confidence) {
        Confidence = confidence;
    }

    public com.aws.imageRekognition.Model.Box getBox() {
        return Box;
    }

    @JsonProperty("Box")
    public void setBox(com.aws.imageRekognition.Model.Box box) {
        Box = box;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCelebrityName() {
        return CelebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        CelebrityName = celebrityName;
    }

    public double getCelebrityScore() {
        return CelebrityScore;
    }

    public void setCelebrityScore(double celebrityScore) {
        CelebrityScore = celebrityScore;
    }

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    @JsonProperty("Landmarks")
    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public List<Emotion> getEmotions() {
        return Emotions;
    }

    public void setEmotions(List<Emotion> emotions) {
        Emotions = emotions;
    }
}
