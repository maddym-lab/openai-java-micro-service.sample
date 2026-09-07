package openai.prompt.using.microservice.java.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.aiplatform.v1.GeminiChatModel;

import java.util.concurrent.ExecutorService;

@Configuration
public class AppConfig {

    @Bean
    public GeminiChatModel vertexAiGeminiChatModel() {
        // Create a new instance of GeminiChatModel
        return GeminiChatModel.newBuilder()
                .setDisplayName("Vertex AI Gemini Chat Model")
                .setModelName("vertex-ai-gemini-chat-model")
                .build();
    }

    @Bean
    public ExecutorService executorService() {
        // Create a new instance of ExecutorService
        return java.util.concurrent.Executors.newFixedThreadPool(10);
    }
}