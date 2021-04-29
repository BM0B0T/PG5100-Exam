package org.tsdes.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
public class ServiceTestBase {

    @Autowired
    private ResetService deleteService;

    @BeforeEach
    public void cleanDatabase(){
        deleteService.resetDatabase();
    }
}
