package openai.prompt.using.microservice.java.demo.models;

import java.util.ArrayList;
import java.util.List;

public class ChatRequestRestCall {

    private String model;
    private List<MessageRestCall> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageRestCall> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageRestCall> messages) {
        this.messages = messages;
    }

    public ChatRequestRestCall(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new MessageRestCall("user", prompt));
    }


}