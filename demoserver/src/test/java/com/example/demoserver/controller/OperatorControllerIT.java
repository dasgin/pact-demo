package com.example.demoserver.controller;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import com.example.demoserver.domain.Operator;
import com.example.demoserver.model.request.OperatorFilterRequest;
import com.example.demoserver.service.OperatorService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRestPactRunner.class)
@Provider("test_demoserver")
@PactBroker(host = "localhost", port = "80")
@Consumer("test_democlient")
public class OperatorControllerIT {

    @TestTarget
    public MockMvcTarget mockMvcTarget = new MockMvcTarget();

    @InjectMocks
    private OperatorController operatorController;

    @Mock
    private OperatorService operatorService;

    @Before
    public void setUp() {
        mockMvcTarget.setControllers(operatorController);
    }

    @State("test GET")
    public void it_should_return_operator_info() {
        Operator operator = new Operator();
        operator.setId(1L);
        operator.setName("server-name");
        operator.setSurname("server-surname");
        operator.setRole("admin");
        given(operatorService.getOperatorById(1L))
                .willReturn(operator);
    }

    @State("test filter")
    public void it_should_filter_operator_info() {
        Operator operator1 = new Operator();
        operator1.setId(1L);
        operator1.setRole("developer");

        Operator operator2 = new Operator();
        operator2.setId(2L);
        operator2.setRole("developer");
        given(operatorService.filterOperators(any(OperatorFilterRequest.class)))
                .willReturn(Arrays.asList(operator1, operator2));
    }

    @State("test POST")
    public void it_should_save_operator() {
    }
}
