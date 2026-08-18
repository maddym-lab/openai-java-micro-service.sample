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
        return new OpenAiImageModel(new OpenAiImageApi(System.getenv("GOOGLE_GEMINI_API_KEY")));
    }

    @Bean
    @Validated
    VertexAiGeminiChatModel googleGeminiChatModel() {
        return new VertexAiGeminiChatModel(new OpenAiApi(System.getenv("GOOGLE_GEMINI_API_KEY")));
    }

    @Bean
    VertexAiGeminiChatModel vertexAiGeminiChatModel() {
        return new VertexAiGeminiChatModel(new OpenAiApi(System.getenv("GOOGLE_GEMINI_API_KEY")));
    }

    @Bean
    OpenAiChatModel openAiChatModel() {
        return new OpenAiChatModel(new OpenAiApi(System.getenv("OPENAI_API_KEY")));
    }

    @Bean
    public AppConfig appConfig() {
        return new AppConfig(openAiChatModel(), googleGeminiChatModel());
    }

    @Bean
    public ChatRequestRestCall chatRequestRestCall() {
        return new ChatRequestRestCall(appConfig().getOpenAiChatModel(), appConfig().getVertexAiGeminiChatModel());
    }

    public DemoApplication(OpenAiChatModel openAiChatModel, VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        this.openAiChatModel = openAiChatModel;
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
    }

    private final OpenAiChatModel openAiChatModel;
    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;
}

class AppConfig {

    private final OpenAiChatModel openAiChatModel;
    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;

    public AppConfig(OpenAiChatModel openAiChatModel, VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        this.openAiChatModel = openAiChatModel;
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
    }

    public OpenAiChatModel getOpenAiChatModel() {
        return openAiChatModel;
    }

    public VertexAiGeminiChatModel getVertexAiGeminiChatModel() {
        return vertexAiGeminiChatModel;
    }

}

class ChatRequestRestCall {

    private final OpenAiChatModel openAiChatModel;
    private final VertexAiGeminiChatModel vertexAiGeminiChatModel;

    public ChatRequestRestCall(OpenAiChatModel openAiChatModel, VertexAiGeminiChatModel vertexAiGeminiChatModel) {
        this.openAiChatModel = openAiChatModel;
        this.vertexAiGeminiChatModel = vertexAiGeminiChatModel;
    }

    public void sendChatRequest(ChatMessage chatMessage) {
        // Implement logic to send chat request using OpenAiChatModel and VertexAiGeminiChatModel
    }

}

class VertexAiGeminiChatModel {

    private final OpenAiApi openAiApi;

    public VertexAiGeminiChatModel(OpenAiApi openAiApi) {
        this.openAiApi = openAiApi;
    }

    public ChatMessage sendChatMessage(String message) {
        // Implement logic to send chat message using VertexAiGeminiChatModel
    }

}