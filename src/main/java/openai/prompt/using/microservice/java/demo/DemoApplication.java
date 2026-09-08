package openai.prompt.using.microservice.java.demo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import com.google.cloud.vertexai.Model;
import com.google.cloud.vertexai.ChatMessage;

import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @Validated
    ImageModel imageModel() {
        return new OpenAiImageModel(new OpenAiImageApi(System.getenv("OPENAI_API_KEY")));
    }

    @Bean
    @Validated
    ChatModel chatModel() {
        return new OpenAiChatModel(new OpenAiApi(System.getenv("OPENAI_API_KEY")));
    }

    @Bean
    @Validated
    ImageModel googleGeminiImageModel() {
        Properties properties = new Properties();
        properties.put("GOOGLE_CLOUD_PROJECT", System.getenv("GOOGLE_CLOUD_PROJECT"));
        properties.put("GOOGLE_APPLICATION_CREDENTIALS", System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
        return ModelServiceFactory.getVertexAiImageModel(properties);
    }

    @Bean
    @Validated
    ChatModel googleGeminiChatModel() {
        Properties properties = new Properties();
        properties.put("GOOGLE_CLOUD_PROJECT", System.getenv("GOOGLE_CLOUD_PROJECT"));
        properties.put("GOOGLE_APPLICATION_CREDENTIALS", System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
        return ModelServiceFactory.getVertexAiChatModel(properties);
    }

    @Bean
    VertexAiGeminiChatModel vertexAiGeminiChatModel() {
        return new VertexAiGeminiChatModel(new VertexAiGeminiApi(System.getenv("GOOGLE_GEMINI_API_KEY")));
    }

    @Bean
    AppConfig appConfig() {
        return new AppConfig(vertexAiGeminiChatModel());
    }

    public DemoApplication(VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
    }

    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;
}