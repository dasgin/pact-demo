package com.example.democlient.controller;

import com.example.democlient.domain.User;
import com.example.democlient.builder.UserBuilder;
import com.example.democlient.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @Test
    public void it_should_user_info_by_user_id(){
        // Given
        User expectedUser = UserBuilder.anUser().id(1L).name("username").build();
        given(userService.fetchUser(1L)).willReturn(expectedUser);

        // When
        User actualUser = controller.fetchUserBy(1L);

        // Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

}