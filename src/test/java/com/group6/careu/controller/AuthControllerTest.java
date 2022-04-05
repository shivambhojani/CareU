package com.group6.careu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.careu.entity.User;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.LoginSuccessHandler;
import com.group6.careu.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Bijitashya on 03, 2022
 */
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserServiceImpl userServiceImpl;

    @MockBean
    LoginSuccessHandler loginSuccessHandler;

    @MockBean
    UserRepository userRepository;

    @Mock
    Model model;

    AuthController authController;

    @Autowired
    private WebApplicationContext context;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authController = new AuthController(userServiceImpl);
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();

        user = User.builder()
                .firstName("Ramesh")
                .lastName("Singh")
                .email("ramesh@gmail.com")
                .gender("male")
                .phone("11254")
                .password("daer4566")
                .enabled(true)
                .role("doctor")
                .build();
    }


    @Test
    void saveUser() throws Exception {
        when(userServiceImpl.save(user)).thenReturn(true);
        assertThat(userServiceImpl.save(user)).isEqualTo(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/register/user"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

    }

    @Test
    void showLandingPage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void showRegisterForm() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("user", new User()))
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }

    @Test
    void showLoginForm() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("user", new User()))
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    void loginUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/login"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        String viewName = authController.loginUser(model);
        assertEquals("login", viewName);

    }

    @Test
    void redirectToDoctor() {
        String viewName = authController.redirectToDoctor();
        assertEquals("doctor", viewName);
    }

    @Test
    void redirectToPatient() {
        String viewName = authController.redirectToPatient();
        assertEquals("patient", viewName);
    }
}