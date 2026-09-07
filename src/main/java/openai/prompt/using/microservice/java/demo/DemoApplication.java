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

    private static class VertexAiGeminiChatModel extends OpenAiChatModel {

        public VertexAiGeminiChatModel(OpenAiApi openAiApi) {
            super(openAiApi);
        }

        @Override
        public ChatMessage process(String input) {
            // Implement Gemini-specific logic here
            return super.process(input);
        }

    }

}