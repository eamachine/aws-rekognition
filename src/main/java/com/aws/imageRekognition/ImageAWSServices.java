package com.aws.imageRekognition;

import com.aws.imageRekognition.Model.Box;
import com.aws.imageRekognition.Model.ImageRekognition;
import com.aws.imageRekognition.Model.Moderation;
import com.aws.imageRekognition.Model.ModerationLabel;
import com.google.gson.Gson;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ImageAWSServices {

    @Async("asyncExecutor")
    public CompletableFuture<DetectLabelsResponse> DetectObjectAndSceneService(RekognitionClient rekognitionClient, Image image) {
        return  CompletableFuture.completedFuture(rekognitionClient.detectLabels(DetectLabelsRequest.builder().image(image).build()));
    }

    @Async("asyncExecutor")
    public CompletableFuture<DetectFacesResponse> DetectFacesResponseService(RekognitionClient rekognitionClient, Image image) {
        return  CompletableFuture.completedFuture(rekognitionClient.detectFaces(DetectFacesRequest.builder().image(image).build()));
    }

    @Async("asyncExecutor")
    public CompletableFuture<DetectModerationLabelsResponse> DetectModerationLabelsService(RekognitionClient rekognitionClient, Image image) {
        return CompletableFuture.completedFuture(rekognitionClient.detectModerationLabels(DetectModerationLabelsRequest.builder().image(image).build()));
    }

    @Async("asyncExecutor")
    public CompletableFuture<RecognizeCelebritiesResponse> DetectCelebrityService(RekognitionClient rekognitionClient, Image image) {
        return  CompletableFuture.completedFuture(rekognitionClient.recognizeCelebrities(RecognizeCelebritiesRequest.builder().image(image).build()));
    }

    public String DetectObjectAndSceneProcess(DetectLabelsResponse response) {
        return new Gson().toJson(response.labels()).toString();
    }

    public String DetectFacesProcess(DetectFacesResponse response, ImageRekognition imageRekognition, double height, double width) {

        imageRekognition.getFaces().addAll(response.faceDetails().
                stream().map(face ->
                new com.aws.imageRekognition.Model.Face(
                        face.confidence(),
                        new Box(face.boundingBox().width() * width, face.boundingBox().height() * height, face.boundingBox().left() * width, face.boundingBox().top() * height),
                        face.ageRange() != null ? face.ageRange().high() : 0,
                        face.gender() != null ? face.gender().toString() : null,
                        null,
                        0,
                        face.landmarks() != null && !face.landmarks().isEmpty() ? face.landmarks().stream().map(l -> new com.aws.imageRekognition.Model.Landmark(l.typeAsString(), l.x() * width, l.y() * height
                        )).collect(Collectors.toList()) :null,
                        face.emotions() != null && !face.emotions().isEmpty() ? face.emotions().stream().map(e ->new com.aws.imageRekognition.Model.Emotion(e.typeAsString(),e.confidence().doubleValue())).collect(Collectors.toList()) : null
                )).collect(Collectors.toList()));

        return new Gson().toJson(response.faceDetails()).toString();
    }

    public String DetectModerationProcess(DetectModerationLabelsResponse response, ImageRekognition imageRekognition) {

        AtomicReference<Double> AdultScore  = new AtomicReference<>((double) 0);
        AtomicReference<Double> SpicyScore  = new AtomicReference<>((double) 0);
        List<com.aws.imageRekognition.Model.ModerationLabel> moderationLabelList = new ArrayList<com.aws.imageRekognition.Model.ModerationLabel>();

        response.moderationLabels().forEach(l -> {
            AdultScore.set(l.confidence().doubleValue() > AdultScore.get() ? l.confidence().doubleValue() : AdultScore.get());

            if(l.name().contains("Suggestive") || l.name().contains("Nudity") && l.confidence().doubleValue() > SpicyScore.get()){
                SpicyScore.set(l.confidence().doubleValue());
            }
            moderationLabelList.add(new ModerationLabel(l.parentName(), l.name(),l.confidence()));
        });

        imageRekognition.setModeration(new Moderation(AdultScore.get() > 0, AdultScore.get(), SpicyScore.get() > 0, SpicyScore.get(),moderationLabelList));

        return new Gson().toJson(response.moderationLabels()).toString();
    }

    public String DetectCelebrityProcess(RecognizeCelebritiesResponse response, ImageRekognition imageRekognition) {

        for(int i=0; i < response.celebrityFaces().size(); i++){
            imageRekognition.getFaces().get(i).setCelebrityName(response.celebrityFaces().get(i).name());
            imageRekognition.getFaces().get(i).setCelebrityScore(response.celebrityFaces().get(i).matchConfidence());
        }

        return new Gson().toJson(response.celebrityFaces()).toString();
    }
}
