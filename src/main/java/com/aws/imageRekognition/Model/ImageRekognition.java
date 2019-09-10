package com.aws.imageRekognition.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

import java.util.ArrayList;
import java.util.List;

public class ImageRekognition {

    private String JsonResults;

    private List<Face> Faces;

    private Moderation Moderation;

    public ImageRekognition() {
        Faces = new ArrayList<Face>();
    }

    public String getJsonResults() {
        return JsonResults;
    }

    @JsonProperty("JsonResults")
    @JsonRawValue
    public void setJsonResults(String jsonResults) {
        JsonResults = jsonResults;
    }

    public List<Face> getFaces() {
        return Faces;
    }

    @JsonProperty("Faces")
    public void setFaces(List<Face> faces) {
        Faces = faces;
    }

    public com.aws.imageRekognition.Model.Moderation getModeration() {
        return Moderation;
    }

    @JsonProperty("Moderation")
    public void setModeration(com.aws.imageRekognition.Model.Moderation moderation) {
        Moderation = moderation;
    }
}
