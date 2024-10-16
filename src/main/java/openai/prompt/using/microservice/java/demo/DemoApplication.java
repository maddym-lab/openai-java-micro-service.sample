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

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ImageModel imageModel() {
		return new OpenAiImageModel(new OpenAiImageApi(System.getenv("OPENAI_API_KEY")));
	}

	@Bean
	ChatModel chatModel() {
		return new OpenAiChatModel(new OpenAiApi(System.getenv("OPENAI_API_KEY")));
	}
}
