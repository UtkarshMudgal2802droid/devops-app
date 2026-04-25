package com.rcoem.devops.model;

public class BuildInfo {
    private String buildNumber;
    private String environment;
    private String deployedAt;
    private String status;

    public BuildInfo(String buildNumber, String environment,
                     String deployedAt, String status) {
        this.buildNumber = buildNumber;
        this.environment = environment;
        this.deployedAt  = deployedAt;
        this.status      = status;
    }

    public String getBuildNumber() { return buildNumber; }
    public String getEnvironment() { return environment; }
    public String getDeployedAt()  { return deployedAt; }
    public String getStatus()      { return status; }
}