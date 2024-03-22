package org.springframework.ai.openai.samples.helloworld.simple;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ai.openai.samples.helloworld.model.ChatRequest;
import org.springframework.ai.openai.samples.helloworld.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
public class SimpleAiController {

	@Value("${openai.model}")
	private String model;

	@Value("${openai.api.url}")
	private String apiUrl;

	@Value("${openai.api.key}")
    private String openaiApiKey;

	@PostMapping("/search")
	@ResponseBody
	public String getResponse(@RequestParam("question") String question) {

		// Create HttpHeaders and set the authorization header with the bearer token
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(openaiApiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create HttpEntity with headers and request body
		Message message = new Message("user", question);
		ArrayList<Message> arrayList = new ArrayList<>();
		arrayList.add(message);
		ChatRequest requestBody = new ChatRequest(model, arrayList);
		HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(requestBody, headers);

		// Create RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Make the POST request
		ResponseEntity<String> response = restTemplate.exchange(
				apiUrl,
				HttpMethod.POST,
				requestEntity,
				String.class // Change String.class to your expected response type
		);

		// Handle the response as needed
		HttpStatus statusCode = response.getStatusCode();
		String responseBody = response.getBody();

		// Parse JSON string
        assert responseBody != null;
        JSONObject jsonObject = new JSONObject(responseBody);
		String content = "";

				// Fetch message content
		JSONArray choices = jsonObject.getJSONArray("choices");
		if (!choices.isEmpty()) {
			JSONObject choice = choices.getJSONObject(0);
			JSONObject message1 = choice.getJSONObject("message");
			content = message1.getString("content");
			System.out.println("Response Status: " + statusCode);
		} else {
			System.out.println("No choices found.");
		}
		return content;
	}
}

