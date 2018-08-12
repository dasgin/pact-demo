package com.example.demoserver.service;

import com.example.demoserver.domain.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperatorService.class);

    public Operator getOperatorById(Long id) {
        return null;
    }

    public boolean save(Operator operator) {
        LOGGER.info("saved: " + operator);
        return true;
    }
}
