package openai.prompt.using.microservice.java.demo.models;

import java.util.List;

public class ChatResponseRestCall {

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    private List<Choice> choices;

    public static class Choice {

        private int index;
        private MessageRestCall message;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public MessageRestCall getMessage() {
            return message;
        }

        public void setMessage(MessageRestCall message) {
            this.message = message;
        }

    }
}
