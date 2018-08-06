package com.example.democlient.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.democlient.domain.User;
import com.example.democlient.domain.builder.UserBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoServerApiUserGetContractIT {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_demoserver", this);

    @Pact(consumer = "test_democlient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/operators/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"id\":1,\"name\":\"server-name\",\"surname\":\"server-surname\",\"role\":\"admin\"}")
                .toPact();
    }

    @Test
    @PactVerification
    public void it_should_fetch_user_by_id() {
        // Given
        User expectedUser = UserBuilder.anUser().id(1L).name("server-name").surname("server-surname").role("admin").build();

        // When
        ResponseEntity<User> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/operators/1", User.class);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        User actualUser = response.getBody();
        assertThat(actualUser).isEqualTo(expectedUser);
    }
}
