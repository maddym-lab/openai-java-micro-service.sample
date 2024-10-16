package openai.prompt.using.microservice.java.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromptRequest {
    @JsonProperty("model")
    private String model;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("height")
    private int height;

    @JsonProperty("width")
    private int width;

    @JsonProperty("numOfResponses")
    private int numOfResponses;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNumOfResponses() {
        return numOfResponses;
    }

    public void setNumOfResponses(int numOfResponses) {
        this.numOfResponses = numOfResponses;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public PromptRequest(String model, String prompt, int height, int width, int numOfResponses){
        this.model = model;
        this.prompt = prompt;
        this.height = height;
        this.width = width;
        this.numOfResponses = numOfResponses;
    }
}
