package com.example.democlient.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.democlient.domain.User;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.example.democlient.domain.builder.UserBuilder.anUser;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoServerApiFilterUserContractIT {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_demoserver", this);

    @Pact(consumer = "test_democlient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test filter")
                .uponReceiving("GET REQUEST")
                .path("/operators")
                .query("role=developer")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("[{\"id\":1,\"role\":\"developer\"}," +
                        "{\"id\":2,\"role\":\"developer\"}]")
                .toPact();
    }

    @Test
    @PactVerification
    public void it_should_filter_users() {
        // Given
        User user1 = anUser().id(1L).role("developer").build();
        User user2 = anUser().id(2L).role("developer").build();

        // When
        ResponseEntity<User[]> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/operators?role=developer", User[].class);

        // Then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        User[] responseBody = response.getBody();
        assertThat(responseBody[0]).isEqualToComparingFieldByField(user1);
        assertThat(responseBody[1]).isEqualToComparingFieldByField(user2);
    }
}
