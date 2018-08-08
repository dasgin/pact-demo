package com.example.democlient.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.example.democlient.domain.User;
import com.example.democlient.domain.builder.UserBuilder;
import com.example.democlient.service.UserFetcherService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServerApiUserGetContractIT {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_demoserver", "localhost", 8080,this);

    @Pact(consumer = "test_democlient")
    public RequestResponsePact createPact(PactDslWithProvider builder){
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
                    .body("{\"id\":1,\"name\":\"server-name\",\"surname\":\"server-surname\",\"role\":\"manager\"}")
                .toPact();
    }

    @Test
    @PactVerification
    public void it_should_fetch_user_by_id(){
        // Given
        User expectedUser = UserBuilder.anUser().id(1L).name("server-name").surname("server-surname").role("manager").build();

        RestTemplate restTemplate = restTemplateBuilder.rootUri(mockProvider.getUrl()).build();
        UserFetcherService service = new UserFetcherService(restTemplate);

        // When
    //    ResponseEntity<User> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/operators/1", User.class);
        User actualUser = service.fetchUser(1L);

        // Then
      //  assertThat(response.getStatusCode().value()).isEqualTo(200);
      //  assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

      //  User actualUser = response.getBody();
        assertThat(actualUser).isEqualTo(expectedUser);

    }


}
