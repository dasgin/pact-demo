package com.example.democlient.service;

import com.example.democlient.domain.User;
import com.example.democlient.model.request.UserFilterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.net.URI;
import java.util.List;

import static com.example.democlient.domain.builder.UserBuilder.anUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void it_should_process_on_fetched_data() throws JsonProcessingException {
        // Given
        User expectedUser = anUser().id(1L).name("name").surname("surname").role("developer").build();
        String userJson = objectMapper.writeValueAsString(expectedUser);
        server.expect(requestTo("/operators/1")).andRespond(withSuccess(userJson, MediaType.APPLICATION_JSON));

        // When
        User actualUser = service.fetchUser(1L);

        // Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    public void it_should_save_user() throws JsonProcessingException {
        //given
        User user = anUser().id(1L).name("name").surname("surname").role("developer").build();
        String string = objectMapper.writeValueAsString(user);

        server.expect(requestTo("/operators"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(string))
                .andRespond(MockRestResponseCreators.withCreatedEntity(URI.create("/operators")));

        //when
        service.createUser(user);

        //then
        server.verify();
    }

    @Test
    public void it_should_filter_users() throws JsonProcessingException {
        //given
        User user1 = anUser().id(1L).name("name1").surname("surname").role("developer").build();
        User user2 = anUser().id(2L).name("name2").surname("surname").role("manager").build();
        User user3 = anUser().id(3L).name("name3").surname("surname").role("developer").build();

        User[] users = {user1, user3};
        String usersJson = objectMapper.writeValueAsString(users);

        server.expect(requestTo("/operators?role=developer")).andRespond(withSuccess(usersJson, MediaType.APPLICATION_JSON));

        //when
        List<User> developers = service.filterUsers(new UserFilterRequest("developer"));

        //then
        then(developers).containsExactly(user1, user3);
    }
}
