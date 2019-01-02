package com.example.democlient.service;

import com.example.democlient.domain.User;
import com.example.democlient.model.request.UserCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static com.example.democlient.builder.UserBuilder.anUser;
import static com.example.democlient.builder.UserCreateRequestBuilder.anUserCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private RestTemplate restTemplate;


    @Test
    public void it_should_process_on_fetched_data() {
        // Given
        User expectedUser = anUser().id(1L).name("name").surname("surname").role("developer").build();
        given(restTemplate.getForObject("/operators/{userId}", User.class, 1L))
                .willReturn(expectedUser);

        // When
        User actualUser = service.fetchUser(1L);

        // Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    public void it_should_save_user() {
        //given
        UserCreateRequest userCreateRequest = anUserCreateRequest().name("name").surname("surname").role("developer").build();


        //when

        service.createUser(userCreateRequest);

        //then
        verify(restTemplate).postForEntity("/operators", userCreateRequest, Void.class);
    }
}
