package openai.prompt.using.microservice.java.demo.models;

public class MessageRestCall {

    private String role;

    private String prompt;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public MessageRestCall(String role, String prompt){
        this.role = role;
        this.prompt = prompt;
    }

}

