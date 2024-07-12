package com.lyftzeigen.server.service;

import com.lyftzeigen.server.dto.SystemInfoDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

@Service
public class HealthCheckService {

    public SystemInfoDto healthCheck(){
        return new SystemInfoDto();
    }
}
