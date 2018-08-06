package com.example.demoserver.controller;

import com.example.demoserver.domain.Operator;
import com.example.demoserver.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    @Autowired
    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping("/{id}")
    public Operator fetchOperatorBy(@PathVariable Long id){
        Operator operator = new Operator();
        operator.setId(id);
        operator.setName("server-name");
        operator.setSurname("server-surname");
        operator.setRole("manager");

        operatorService.save(operator);

        return operator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOperator(@RequestBody Operator operator){
        operatorService.save(operator);
    }

}
