package org.springframework.ai.openai.samples.helloworld.simple;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@RestController
@Controller
public class SimpleAiController {

	private final ChatClient chatClient;

	@Autowired
	public SimpleAiController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@RequestMapping("/")
	public String showAskPage() {
		return "ask";
	}

	//@GetMapping("/ai/simple/{question}")
	@PostMapping("/ask")
	@ResponseBody
	public String completion(@RequestParam("question") String question) {
		Map chatData = Map.of("generation", chatClient.call(question));
		return (String) chatData.get("generation");
	}
}
