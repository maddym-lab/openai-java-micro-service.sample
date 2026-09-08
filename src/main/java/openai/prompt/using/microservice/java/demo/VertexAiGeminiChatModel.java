package openai.prompt.using.microservice.java.demo;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.prometheus.client.Histogram;
import io.prometheus.client.Counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.gemini.v1beta1.GeminiServiceClient;
import com.google.cloud.gemini.v1beta1.Model;
import com.google.cloud.gemini.v1beta1.Prompt;
import com.google.cloud.gemini.v1beta1.PromptResponse;

import openai.prompt.using.microservice.java.demo.config.GeminiConfig;
import openai.prompt.using.microservice.java.demo.model.ChatModel;

@Service
public class VertexAiGeminiChatModel {

    private final GeminiServiceClient geminiServiceClient;

    @Autowired
    public VertexAiGeminiChatModel(GeminiConfig geminiConfig) {
        this.geminiServiceClient = GeminiServiceClient.create(geminiConfig.getGeminiCredentials());
    }

    public CompletableFuture<PromptResponse> generateChatResponse(Prompt prompt) {
        try {
            Histogram histogram = Histogram.create("gemini_request_duration");
            histogram.startTimer();

            Model model = Model.newBuilder()
                    .setName("vertex-ai-gemini-chat-model")
                    .setModelId(UUID.randomUUID().toString())
                    .build();

            PromptResponse response = geminiServiceClient.generateText(model, prompt);

            histogram.observe(System.currentTimeMillis());
            return CompletableFuture.completedFuture(response);
        } catch (InterruptedException | ExecutionException e) {
            Counter counter = Counter.create("gemini_request_errors");
            counter.inc();
            return CompletableFuture.failedFuture(e);
        }
    }
}