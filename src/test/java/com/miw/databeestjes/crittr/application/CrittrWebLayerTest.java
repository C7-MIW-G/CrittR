package com.miw.databeestjes.crittr.application;

import com.miw.databeestjes.crittr.controller.HomepageController;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomepageController.class) // Without a parameter this instantiates all controllers
public class CrittrWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrittrUserDetailsService userDetailsService;

    @Test // This tests the HTTP request by only instantiating the web layer instead of the whole context
    public void shouldReturnWelcomeMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to your CrittR Petting Zoo!")));
    }
}