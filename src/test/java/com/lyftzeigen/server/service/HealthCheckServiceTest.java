package com.lyftzeigen.server.service;

import com.lyftzeigen.server.dto.SystemInfoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckServiceTest {
    private final SystemInfoDto systemInfoDto = new SystemInfoDto();

    @Test
    void healthCheck() {
        System.out.println(systemInfoDto);
    }
}