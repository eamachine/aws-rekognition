package com.aws.imageRekognition.Model;

import java.util.List;

public class Moderation {

    private boolean IsAdultScore;
    private double AdultScore;
    private boolean IsSpicy;
    private double SpicyScore;
    private List<ModerationLabel> ModerationLabelList;

    public Moderation(boolean isAdultScore, double adultScore, boolean isSpicy, double isSpicyScore, List<ModerationLabel> moderationLabelList) {
        IsAdultScore = isAdultScore;
        AdultScore = adultScore;
        IsSpicy = isSpicy;
        SpicyScore = isSpicyScore;
        ModerationLabelList = moderationLabelList;
    }

    public boolean isAdultScore() {
        return IsAdultScore;
    }

    public void setAdultScore(boolean adultScore) {
        IsAdultScore = adultScore;
    }

    public double getAdultScore() {
        return AdultScore;
    }

    public void setAdultScore(double adultScore) {
        AdultScore = adultScore;
    }

    public boolean isSpicy() {
        return IsSpicy;
    }

    public void setSpicy(boolean spicy) {
        IsSpicy = spicy;
    }

    public double getIsSpicyScore() {
        return SpicyScore;
    }

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
