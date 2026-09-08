package openai.prompt.using.microservice.java.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.cloud.gemini.v1beta1.GeminiApiGrpc;
import com.google.cloud.gemini.v1beta1.GeminiServiceClient;

public class VertexAiGeminiApi {

    private static final String GEMINI_API_KEY = "YOUR_GEMINI_API_KEY";
    private static final String GEMINI_API_ENDPOINT = "https://gemini.googleapis.com/v1beta1";

    private final GeminiServiceClient client;

    public VertexAiGeminiApi() {
        client = GeminiServiceClient.create(GEMINI_API_ENDPOINT);
    }

    public String query(String query) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GEMINI_API_ENDPOINT + "/v1beta1/projects/-/locations/-/operations"))
                .header("Authorization", "Bearer " + GEMINI_API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        HttpResponse<String> response = client.blockingStub().execute(request);
        return response.body();
    }

    public static void main(String[] args) throws IOException {
        VertexAiGeminiApi api = new VertexAiGeminiApi();
        String query = "Your query here";
        String result = api.query(query);
        System.out.println(result);
    }
}