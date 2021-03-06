package com.miw.databeestjes.crittr.testing.integrationtesting;

import com.miw.databeestjes.crittr.controller.HomepageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Injects Spring's MockMvc
public class CrittrWebApplicationTest {

    @Autowired
    private HomepageController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test // This is a simple sanity/smoke test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test // This tests the HTTP request without starting the server as opposed to CrittrHttpRequestTest
    public void shouldReturnWelcomeMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to your CrittR Petting Zoo!")));
    }
}
