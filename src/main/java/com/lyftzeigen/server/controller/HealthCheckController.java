package com.lyftzeigen.server.controller;

import com.lyftzeigen.server.dto.SystemInfoDto;
import com.lyftzeigen.server.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthCheckController {
    HealthCheckService healthCheckService;

    @Autowired
    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/healthcheck")
    public ResponseEntity<SystemInfoDto> healthCheck(){
       return new ResponseEntity<>(healthCheckService.healthCheck(), HttpStatus.OK);
    }
}
