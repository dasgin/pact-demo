package com.example.democlient.controller;

import com.example.democlient.domain.User;
import com.example.democlient.builder.UserBuilder;
import com.example.democlient.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void it_should_get_user_info_by_user_id() throws Exception {
        // Given
        User expectedUser = UserBuilder.anUser().name("username").surname("surname").id(1L).role("developer").build();
        given(userService.fetchUser(1L)).willReturn(expectedUser);

        // When
        mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("username")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.role", is("developer")));
    }
}
