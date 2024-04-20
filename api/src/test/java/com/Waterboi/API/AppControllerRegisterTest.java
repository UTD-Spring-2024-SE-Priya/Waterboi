package com.Waterboi.API;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AppControllerRegisterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerShouldReturnRegisterPage() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    //test case 1
    @Test
    public void whenRegisterAppuser_theAppuserIsRegistered() throws Exception {

        mockMvc.perform(post("/register")
                        .param("username", "newUser2@gmail.com")
                        .param("password", "password123")
                        .param("passwordConfirm", "password123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    //Test case 2
    @Test
    public void whenRegisterAppuser_confirmPasswordInvalid_throwException() throws Exception{
        mockMvc.perform(post("/register")
                        .param("username", "newUser@gmail.com")
                        .param("password", "password123")
                        .param("passwordConfirm", "password1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?error"));
    }
    //Test case 3
    @Test
    public void whenRegisterAppuser_PasswordIncorrectLength_throwException() throws Exception{
        mockMvc.perform(post("/register")
                        .param("username", "newUser@gmail.com")
                        .param("password", "pass")
                        .param("passwordConfirm", "pass")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?error"));
    }

    //Test case 4
    @Test
    public void whenRegisterAppuser_passwordMissing_confirmPasswordPresent_throwException() throws Exception{
        mockMvc.perform(post("/register")
                        .param("username", "newUser@gmail.com")
                        .param("password", "password123")
                        .param("passwordConfirm", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?error"));
    }

    //Test case 5
    @Test
    public void whenRegisterAppuser_emailInvalid_throwException() throws Exception{
        mockMvc.perform(post("/register")
                        .param("username", "invalidEmail")
                        .param("password", "password123")
                        .param("passwordConfirm", "password123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?error"));
    }

    //Test case 6
    @Test
    public void whenRegisterAppuser_emailMissing_throwException() throws Exception{
        mockMvc.perform(post("/register")
                        .param("username", "")
                        .param("password", "password123")
                        .param("passwordConfirm", "password123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?error"));
    }
}
