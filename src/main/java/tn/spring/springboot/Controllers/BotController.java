package tn.spring.springboot.Controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tn.spring.springboot.entities.BotRequest;
import tn.spring.springboot.entities.BotResponse;
import tn.spring.springboot.entities.Message;

@RestController
public class BotController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.max-completions}")
    private int maxCompletions;

    @Value("${openai.temperature}")
    private double temperature;

    @Value("${openai.max_tokens}")
    private int maxTokens;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/chat")
    public BotResponse chat(@RequestParam("prompt") String prompt) {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("user", prompt));
        BotRequest request = new BotRequest(model, messages, maxCompletions, temperature, maxTokens);
        BotResponse response = restTemplate.postForObject(apiUrl, request, BotResponse.class);
        return response;
    }
}
