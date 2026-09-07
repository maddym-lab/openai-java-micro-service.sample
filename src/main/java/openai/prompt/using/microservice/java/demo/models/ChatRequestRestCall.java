package openai.prompt.using.microservice.java.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vertexai.Model;
import com.google.cloud.vertexai.ChatMessage;

public class ChatRequestRestCall {

    private String model;
    private List<ChatMessage> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public ChatRequestRestCall(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new ChatMessage("user", prompt));
    }

}