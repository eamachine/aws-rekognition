package com.aws.imageRekognition;

import com.aws.imageRekognition.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*")
public class ImageAnalyzer {

    @Autowired
    private ImageAWSServices imageAWSServices;

    @PostMapping("/AWSImageRecognition")
    @RequestMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    ImageRekognition ImageRecognitionAnalize(@RequestBody byte[] imageBytes, double height, double width) {

        /**Process image to aws sdk from array casting to image*/
        Image image = Image.builder().bytes(SdkBytes.fromByteArray(imageBytes)).build();

        /**Connect to AWS CLI SDK*/
        RekognitionClient rekognitionClient = RekognitionClient.create();

        /**CALL Services in parallel */
        CompletableFuture<DetectLabelsResponse> labelsResponse = imageAWSServices.DetectObjectAndSceneService(rekognitionClient, image);
        CompletableFuture<DetectFacesResponse>  facesResponse = imageAWSServices.DetectFacesResponseService(rekognitionClient, image);
        CompletableFuture<DetectModerationLabelsResponse> moderationLabelsResponse = imageAWSServices.DetectModerationLabelsService(rekognitionClient, image);
        CompletableFuture<RecognizeCelebritiesResponse>  celebritiesResponse = imageAWSServices.DetectCelebrityService(rekognitionClient, image);

        /**Wait for responses*/
        CompletableFuture.allOf(labelsResponse, facesResponse, moderationLabelsResponse, celebritiesResponse).join();

        /**Prepare Object for output*/
        ImageRekognition imageRekognition = new ImageRekognition();
        StringBuilder json = new StringBuilder();

        /**Transform response from AWS to FE*/
        try {
            json.append("{\"DetectObjectAndScene\" :" + imageAWSServices.DetectObjectAndSceneProcess(labelsResponse.get()) + ",");
            json.append("\"DetectFaces\" :" + imageAWSServices.DetectFacesProcess(facesResponse.get(), imageRekognition, height, width) + ",");
            json.append("\"DetectModeration\" :" + imageAWSServices.DetectModerationProcess(moderationLabelsResponse.get(), imageRekognition) + ",");
            json.append("\"DetectCelebrity\" :" + imageAWSServices.DetectCelebrityProcess(celebritiesResponse.get(), imageRekognition) + " }");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        imageRekognition.setJsonResults(json.toString());

        return imageRekognition;
    }
}
