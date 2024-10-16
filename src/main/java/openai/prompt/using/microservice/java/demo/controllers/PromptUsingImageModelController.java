package openai.prompt.using.microservice.java.demo.controllers;

import openai.prompt.using.microservice.java.demo.models.PromptRequest;
import org.springframework.ai.image.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptUsingImageModelController {

    private final ImageModel imageModel;

    public PromptUsingImageModelController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/getimages")
    public String imageGen(@RequestBody PromptRequest imageGenRequest) {


        ImageOptions options = ImageOptionsBuilder.builder()
                .withModel(imageGenRequest.getModel())
                .withN(imageGenRequest.getNumOfResponses())
                .withHeight(imageGenRequest.getHeight())
                .withWidth(imageGenRequest.getWidth())
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(imageGenRequest.getPrompt(), options);

        System.out.println("image fetch started: " + imageGenRequest);
        ImageResponse response = imageModel.call(imagePrompt);
        System.out.println("image fetch complete: " + response);

        return "redirect:" + response.getResult().getOutput().getUrl();
    }
}
