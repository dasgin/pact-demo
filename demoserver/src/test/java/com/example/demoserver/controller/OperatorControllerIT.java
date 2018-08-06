package com.example.demoserver.controller;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import com.example.demoserver.domain.Operator;
import com.example.demoserver.service.OperatorService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRestPactRunner.class)
@Provider("test_demoserver")
@PactFolder("pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperatorControllerIT {

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @MockBean
    private OperatorService operatorService;

    @State("test GET")
    public void it_should_return_operator_info(){

    }

    @State("test POST")
    public void it_should_save_operator(){
        // Given
        given(operatorService.save(any(Operator.class))).willReturn(false);
    }

}
