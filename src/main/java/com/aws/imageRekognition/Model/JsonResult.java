package com.aws.imageRekognition.Model;

public class JsonResult {

    private String Name;

    private String json;

    private double time;

    public JsonResult(String name, String json, double time) {
        Name = name;
        this.json = json;
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
