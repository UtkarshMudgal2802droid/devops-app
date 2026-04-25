package com.rcoem.devops.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class DevopsServiceTest {

    @InjectMocks
    DevopsService devopsService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(devopsService, "env", "dev");
    }

    @Test
    public void testPathDev() {
        String path = devopsService.getPath();
        assert path.equalsIgnoreCase("invoked in dev");
    }

    @Test
    public void testPathProd() {
        ReflectionTestUtils.setField(devopsService, "env", "prod");
        String path = devopsService.getPath();
        assert path.equalsIgnoreCase("invoked in prod");
    }
}