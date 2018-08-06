package com.example.demoserver.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OperatorController.class)
public class OperatorControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_get_user_info_by_user_id() throws Exception {
        // When
        mockMvc.perform(get("/operators/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("server-name")))
                .andExpect(jsonPath("$.surname", is("server-surname")))
                .andExpect(jsonPath("$.role", is("manager")));
    }
}