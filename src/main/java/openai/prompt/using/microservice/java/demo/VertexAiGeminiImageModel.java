package openai.prompt.using.microservice.java.demo;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.google.cloud.gemini.v1beta1.GeminiServiceClient;
import com.google.cloud.gemini.v1beta1.Model;
import com.google.cloud.gemini.v1beta1.ModelServiceClient;
import com.google.cloud.gemini.v1beta1.SearchRequest;
import com.google.cloud.gemini.v1beta1.SearchResponse;

import io.grpc.Status;
import io.grpc.StatusException;

public class VertexAiGeminiImageModel {

    private final GeminiServiceClient geminiServiceClient;

    public VertexAiGeminiImageModel(GeminiServiceClient geminiServiceClient) {
        this.geminiServiceClient = geminiServiceClient;
    }

    public CompletableFuture<SearchResponse> searchImage(String query) {
        Model model = Model.newBuilder()
                .setName("vertex-ai-gemini-image-model")
                .setDisplayName("Vertex AI Gemini Image Model")
                .setDescription("A model for searching images using Vertex AI and Gemini")
                .build();

        SearchRequest searchRequest = SearchRequest.newBuilder()
                .setModel(model)
                .setQuery(query)
                .build();

        return geminiServiceClient.searchAsync(searchRequest)
                .handle((response, throwable) -> {
                    if (throwable != null) {
                        throw new StatusException(throwable);
                    }
                    return response;
                });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        GeminiServiceClient geminiServiceClient = GeminiServiceClient.create();
        VertexAiGeminiImageModel model = new VertexAiGeminiImageModel(geminiServiceClient);
        String query = "image query";
        SearchResponse response = model.searchImage(query).get();
        System.out.println(response);
    }
}