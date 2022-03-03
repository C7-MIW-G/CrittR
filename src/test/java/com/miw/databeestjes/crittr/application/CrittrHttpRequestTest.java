package com.miw.databeestjes.crittr.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

// Starts the server with a random port to avoid conflicts in the test environment
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrittrHttpRequestTest {

    @LocalServerPort // Injects the random port
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test // This tests the HTTP request by starting the server
    public void shouldReturnWelcomeMessage() throws Exception {
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class).contains("Welcome to your CrittR Petting Zoo!"));
    }
}
