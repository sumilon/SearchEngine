package org.springframework.ai.openai.samples.helloworld.model;

import java.util.ArrayList;

public class ChatRequest {

    public String model;
    public ArrayList<Message> messages;

    public ChatRequest(String model, ArrayList<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
}
