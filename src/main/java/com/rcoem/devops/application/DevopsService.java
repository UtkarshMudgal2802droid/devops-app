package com.rcoem.devops.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import static java.util.Objects.nonNull;

@Service
public class DevopsService {

    @Value("${environment}")
    private String env;

    public String getPath() {
        if (nonNull(env) && env.equalsIgnoreCase("prod")) {
            return "invoked in prod";
        }
        return "invoked in " + env;
    }
}