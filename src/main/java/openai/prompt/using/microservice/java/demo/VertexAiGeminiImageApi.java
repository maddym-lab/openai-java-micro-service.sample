package openai.prompt.using.microservice.java.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.cloud.gemini.v1beta1.GeminiApiGrpc;
import com.google.cloud.gemini.v1beta1.ImageApiServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class VertexAiGeminiImageApi {

    private final ImageApiServiceGrpc.ImageApiServiceBlockingStub imageApiService;

    public VertexAiGeminiImageApi(String endpoint) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(endpoint, 443)
                .usePlaintext()
                .build();
        imageApiService = ImageApiServiceGrpc.newBlockingStub(channel);
    }

    public HttpResponse<String> getGeminiImage(String imageId) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("/v1beta1/projects/-/locations/-/images:search"))
                .header("Authorization", "Bearer YOUR_GEMINI_API_KEY")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{
                    "imageId": "