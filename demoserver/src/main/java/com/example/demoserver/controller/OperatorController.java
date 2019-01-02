package com.example.demoserver.controller;

import com.example.demoserver.domain.Operator;
import com.example.demoserver.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    private final OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping("/{id}")
    public Operator fetchOperatorBy(@PathVariable Long id) {
        return operatorService.getOperatorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOperator(@RequestBody Operator operator) {
        operatorService.save(operator);
    }

}
