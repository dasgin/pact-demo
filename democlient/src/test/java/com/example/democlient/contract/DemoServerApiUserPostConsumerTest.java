package com.example.democlient.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.democlient.model.request.UserCreateRequest;
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

import static com.example.democlient.builder.UserCreateRequestBuilder.anUserCreateRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServerApiUserPostConsumerTest {

    @Autowired
    private UserService userService;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2(
            "test_demoserver",
            "localhost",
            9090,
            this
    );

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Pact(consumer = "test_democlient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        PactDslJsonBody body = new PactDslJsonBody()
                .stringValue("name", "client-name")
                .stringValue("surname", "client-surname")
                .stringValue("role", "developer");

        return builder
                .given("post statement")
                .uponReceiving("Post Requests for Operator")
                .method("POST")
                .headers(headers)
                .body(body)
                .path("/operators")
                .willRespondWith()
                .status(201)
                .toPact();
    }

    @Test
    @PactVerification("test_demoserver")
    public void it_should_save_user() {
        // Given
        UserCreateRequest userCreateRequest = anUserCreateRequest()
                .name("client-name")
                .surname("client-surname")
                .role("developer")
                .build();

        // When
        userService.createUser(userCreateRequest);

        // Then
    }
}
