package com.lyftzeigen.server.dto;

import com.lyftzeigen.server.supply.HumanReadableByteCount;
import lombok.Data;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Data
public class SystemInfoDto {
    private String os;
    private String arch;
    private Integer availableProc;
    private Double systemLoadAverage;
    private String freeMemorySI;
    private String freeMemoryBin;
    private String totalMemorySI;
    private String totalMemoryBin;
    private String freeDiskSpaceSI;
    private String freeDiskSpaceBin;
    private List<File> rootList;
    private String hostname;
    private String ipAddress;

    public SystemInfoDto() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem osInfo = si.getOperatingSystem();
        GlobalMemory memory = hal.getMemory();
        CentralProcessor processor = hal.getProcessor();

        this.os = osInfo.toString();
        this.arch = System.getProperty("os.arch");
        this.availableProc = processor.getLogicalProcessorCount();
        this.systemLoadAverage = processor.getSystemLoadAverage(1)[0];

        this.freeMemorySI = FormatUtil.formatBytes(memory.getAvailable());
        this.freeMemoryBin = HumanReadableByteCount.humanReadableByteCountBin(memory.getAvailable());

        this.totalMemorySI = FormatUtil.formatBytes(memory.getTotal());
        this.totalMemoryBin = HumanReadableByteCount.humanReadableByteCountBin(memory.getTotal());

        // Получение информации о свободном дисковом пространстве
        try {
            FileStore fileStore = Files.getFileStore(Paths.get("/"));
            long freeDiskSpace = fileStore.getUsableSpace();
            this.freeDiskSpaceSI = HumanReadableByteCount.humanReadableByteCountSI(freeDiskSpace);
            this.freeDiskSpaceBin = HumanReadableByteCount.humanReadableByteCountBin(freeDiskSpace);
        } catch (Exception e) {
            this.freeDiskSpaceSI = "Unknown";
            this.freeDiskSpaceBin = "Unknown";
        }

        this.rootList = List.of(File.listRoots());

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            this.hostname = inetAddress.getHostName();
            this.ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            this.hostname = "Unknown";
            this.ipAddress = "Unknown";
        }
    }
}
