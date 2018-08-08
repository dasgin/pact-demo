package com.example.democlient.service;

import com.example.democlient.configuration.RestTemplateConfiguration;
import com.example.democlient.domain.User;
import com.example.democlient.domain.builder.UserBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(UserFetcherService.class)
@Import(RestTemplateConfiguration.class)
public class UserFetcherServiceTest {

    @Autowired
    private UserFetcherService service;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void it_should_process_on_fetched_data() throws JsonProcessingException {
        // Given
        User expectedUser = UserBuilder.anUser().id(1L).name("name").surname("surname").role("developer").build();
        String userJson = objectMapper.writeValueAsString(expectedUser);
        server.expect(requestTo("/operators/1")).andRespond(withSuccess(userJson, MediaType.APPLICATION_JSON));

        // When
        User actualUser = service.fetchUser(1L);

        // Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

}
