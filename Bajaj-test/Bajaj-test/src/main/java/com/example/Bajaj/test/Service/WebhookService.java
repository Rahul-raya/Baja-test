package com.example.Bajaj.test.Service;


import com.example.Bajaj.test.Entity.WebhookResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private static final String GENERATE_WEBHOOK_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    private static final String NAME = "Raya Veera Venkata Naga Rahul Reddy";
    private static final String REG_NO = "22BCE8855";
    private static final String EMAIL = "rahul.22bce8855@vitapstudent.ac.in";

    public WebhookResponse generateWebhook() {
        RestTemplate restTemplate = new RestTemplate();
        String requestJson = String.format(
                "{\"name\":\"%s\",\"regNo\":\"%s\",\"email\":\"%s\"}",
                NAME, REG_NO, EMAIL
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<WebhookResponse> response =
                restTemplate.exchange(GENERATE_WEBHOOK_URL, HttpMethod.POST, entity, WebhookResponse.class);

        return response.getBody();
    }
}