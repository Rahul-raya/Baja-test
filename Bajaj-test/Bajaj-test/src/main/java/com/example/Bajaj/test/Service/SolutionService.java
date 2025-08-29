package com.example.Bajaj.test.Service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SolutionService {
    private static final String FINAL_SQL_QUERY =
            "SELECT " +
            "p.AMOUNT AS SALARY, " +
            "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
            "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, " +
            "d.DEPARTMENT_NAME " +
            "FROM PAYMENTS p " +
            "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
            "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
            "WHERE DAY(p.PAYMENT_TIME) <> 1 " +
            "ORDER BY p.AMOUNT DESC " +
            "LIMIT 1;";

    private static final String SUBMIT_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public String submitSolution(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        String body = String.format("{\"finalQuery\":\"%s\"}", FINAL_SQL_QUERY);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                SUBMIT_URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }
}

