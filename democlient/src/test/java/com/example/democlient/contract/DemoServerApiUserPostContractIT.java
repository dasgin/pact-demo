package com.example.democlient.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.democlient.domain.User;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoServerApiUserPostContractIT {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_demoserver", "localhost", 8080,this);

    @Pact(consumer = "test_democlient")
    public RequestResponsePact createPact(PactDslWithProvider builder){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test POST")
                    .uponReceiving("POST REQUEST")
                    .method("POST")
                    .headers(headers)
                    .body("{\"name\":\"client-name\",\"surname\":\"client-surname\",\"role\":\"developer\"}")
                    .path("/operators")
                .willRespondWith()
                    .status(201)
                .toPact();
    }

    @Test
    @PactVerification
    public void it_should_fetch_user_by_id(){
        // Given
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = "{\"name\":\"client-name\",\"surname\":\"client-surname\",\"role\":\"developer\"}";

        // When
        ResponseEntity<User> postResponse = new RestTemplate()
                .exchange(mockProvider.getUrl() + "/operators", HttpMethod.POST, new HttpEntity<>(jsonBody, httpHeaders), User.class);

        // Then
        assertThat(postResponse.getStatusCode().value()).isEqualTo(201);
    }
}
