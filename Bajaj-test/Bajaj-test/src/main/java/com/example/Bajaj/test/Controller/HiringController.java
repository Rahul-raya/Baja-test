package com.example.Bajaj.test.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bajaj.test.Entity.WebhookResponse;
import com.example.Bajaj.test.Service.SolutionService;
import com.example.Bajaj.test.Service.WebhookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HiringController {

    private final WebhookService webhookService;
    private final Object solutionService;

    @GetMapping("/get-token")
    public ResponseEntity<?> getToken() {
        WebhookResponse response = webhookService.generateWebhook();
        return ResponseEntity.ok(response); 
    }

    @PostMapping("/submit-sql")
    public ResponseEntity<?> submitSql(@RequestParam String accessToken) {
        
        String result = ((SolutionService) solutionService).submitSolution(accessToken);
        return ResponseEntity.ok("✅ SQL submitted: " + result);
    }
}
