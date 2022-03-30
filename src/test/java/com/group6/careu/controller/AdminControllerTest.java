package com.group6.careu.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.group6.careu.exceptions.UserNotFoundException;
import com.group6.careu.service.UserServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(this.userServiceImpl).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    @Test
    void testUpdateUserEnabledStatus() throws Exception {
        when(this.userServiceImpl.updateUserEnabledStatus((Integer) any(), anyBoolean())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{id}/enabled/{status}", 1, true);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    @Test
    void testUpdateUserEnabledStatus2() throws Exception {
        when(this.userServiceImpl.updateUserEnabledStatus((Integer) any(), anyBoolean())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/{id}/enabled/{status}", 1, true);
        getResult.contentType("https://example.com/example");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(this.userServiceImpl).delete((Integer) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/delete/{id}", 1);
        getResult.contentType("https://example.com/example");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    @Test
    void testDeleteUser3() throws Exception {
        doThrow(new UserNotFoundException("An error occurred")).when(this.userServiceImpl).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin"));
    }

    @Test
    void testRedirectToAdmin() throws Exception {
        when(this.userServiceImpl.listAll()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

