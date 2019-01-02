package com.example.democlient.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.democlient.domain.User;
import com.example.democlient.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServerApiUserGetConsumerTest {

    @Autowired
    private UserService userService;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_demoserver", "localhost", 9090, this);

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Pact(consumer = "test_democlient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("get statement")
                .uponReceiving("Get Request for Operators")
                .path("/operators/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"id\":1,\"name\":\"server-name\",\"surname\":\"server-surname\",\"role\":\"admin\"}")
                .toPact();
    }

    @Test
    @PactVerification("test_demoserver")
    public void it_should_fetch_user_by_id() {
        // Given

        // When
        User user = userService.fetchUser(1L);

        // Then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("server-name");
        assertThat(user.getSurname()).isEqualTo("server-surname");
        assertThat(user.getRole()).isEqualTo("admin");
    }
}
