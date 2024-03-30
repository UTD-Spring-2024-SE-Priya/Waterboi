package com.Waterboi.API;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiControllerLoginTest {
    private static final String USERNAME_KEY = "username";
    private static final String TEST_USERNAME = "newUser";
    private static final String PASSWORD_KEY = "password";
    private static final String TEST_PASSWORD = "password";
    private static final String PASSWORD_CONFIRM_KEY = "passwordConfirm";
    private static final String LOGIN_URL = "/login";
    private static final String LOGIN_ERROR_URL = "/login?error";
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    void setup() throws Exception {
        mockMvc.perform(post("/register")
                .param(USERNAME_KEY, TEST_USERNAME)
                .param(PASSWORD_KEY, TEST_PASSWORD)
                .param(PASSWORD_CONFIRM_KEY, TEST_PASSWORD)
                .with(csrf()));
    }
    //Test Case 1
    @Test
    public void whenAppuserRegsitered_AppuserCanLogin() throws Exception {

        mockMvc.perform(post(LOGIN_URL)
                        .param(USERNAME_KEY, TEST_USERNAME)
                        .param(PASSWORD_KEY, TEST_PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    //Test 2
    @Test
    public void whenAppuserRegistered_wrongPassword_ThrowException() throws Exception {
        mockMvc.perform(post(LOGIN_URL)
                        .param(USERNAME_KEY, TEST_USERNAME)
                        .param(PASSWORD_KEY, TEST_PASSWORD + "wrong password")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(LOGIN_ERROR_URL));
    }

    //Test 3
    @Test
    public void whenAppuserRegistered_missingPassword_ThrowException() throws Exception {
        mockMvc.perform(post(LOGIN_URL)
                        .param(USERNAME_KEY, TEST_USERNAME)
                        .param(PASSWORD_KEY, "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(LOGIN_ERROR_URL));
    }
    //Test 4
    @Test
    public void whenAppuserRegistered_wrongEmail_ThrowException() throws Exception {
        mockMvc.perform(post(LOGIN_URL)
                        .param(USERNAME_KEY, TEST_USERNAME + "wrong username")
                        .param(PASSWORD_KEY, TEST_PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(LOGIN_ERROR_URL));
    }

    //Test 5
    @Test
    public void whenAppuserRegistered_missingEmail_ThrowException() throws Exception {
        mockMvc.perform(post(LOGIN_URL)
                        .param(USERNAME_KEY, "")
                        .param(PASSWORD_KEY, TEST_PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(LOGIN_ERROR_URL));
    }
}
