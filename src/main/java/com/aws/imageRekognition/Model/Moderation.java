package com.aws.imageRekognition.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Moderation {

    private boolean IsAdultContent;
    private double AdultScore;
    private boolean IsSpicyContent;
    private double SpicyScore;
    private List<ModerationLabel> ModerationLabelList;

    public Moderation(boolean isAdultScore, double adultScore, boolean isSpicy, double isSpicyScore, List<ModerationLabel> moderationLabelList) {
        IsAdultContent = isAdultScore;
        AdultScore = adultScore;
        IsSpicyContent = isSpicy;
        SpicyScore = isSpicyScore;
        ModerationLabelList = moderationLabelList;
    }

    public boolean isAdultContent() {
        return IsAdultContent;
    }

    @JsonProperty("IsAdultContent")
    public void setAdultContent(boolean adultContent) {
        IsAdultContent = adultContent;
    }

    public double getAdultScore() {
        return AdultScore;
    }

    @JsonProperty("AdultScore")
    public void setAdultScore(double adultScore) {
        AdultScore = adultScore;
    }

    public boolean isSpicyContent() {
        return IsSpicyContent;
    }

    @JsonProperty("IsSpicyContent")
    public void setSpicyContent(boolean spicyContent) {
        IsSpicyContent = spicyContent;
    }

    public double getIsSpicyScore() {
        return SpicyScore;
    }

    @JsonProperty("SpicyScore")
    public void setIsSpicyScore(double isSpicyScore) {
        SpicyScore = isSpicyScore;
    }

    public List<ModerationLabel> getModerationLabelList() {
        return ModerationLabelList;
    }

    public void setModerationLabelList(List<ModerationLabel> moderationLabelList) {
        this.ModerationLabelList = moderationLabelList;
    }
}
