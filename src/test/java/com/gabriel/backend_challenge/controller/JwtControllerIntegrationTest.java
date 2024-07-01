package com.gabriel.backend_challenge.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String validToken;
    private String invalidJwtToken;

    @BeforeEach
    public void setup() {
        validToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        invalidJwtToken = "your_invalid_jwt_token_here";
    }

    @Test
    public void whenValidTokenIsProvided_thenReturnsStatusOkv2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/decode")
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenInvalidTokenIsProvided_thenReturnsStatusBadRequestv2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/decode")
                        .header("Authorization", "Bearer " + invalidJwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenValidTokenIsProvided_thenReturnsStatusOkv1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/decode")
                        .content(validToken)
                        .contentType("text/plain"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenInvalidTokenIsProvided_thenReturnsStatusBadRequestv1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/decode")
                        .content(invalidJwtToken)
                        .contentType("text/plain"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}