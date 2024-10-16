package openai.prompt.using.microservice.java.demo.controllers;

import openai.prompt.using.microservice.java.demo.models.PromptRequest;
import openai.prompt.using.microservice.java.demo.models.ChatRequestRestCall;
import openai.prompt.using.microservice.java.demo.models.ChatResponseRestCall;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PromptUsingRestTemplateController {
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String apiUrl;

    //@Value("${openai.model.gpt40-mini}")
    //private String defaultModel;

    @GetMapping("/chat")
    public String chat(@RequestBody PromptRequest requestPayload) {
        //if(requestPayload.getModel().isEmpty())
        //   requestPayload.setModel(defaultModel);
        // create a request
        ChatRequestRestCall request = new ChatRequestRestCall(requestPayload.getModel(), requestPayload.getPrompt());

        // call the API
        ChatResponseRestCall response = restTemplate.postForObject(apiUrl, request, ChatResponseRestCall.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getPrompt();
    }
}
