package com.group6.careu.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.service.DoctorAvailabilityService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DoctorAvailabilityController.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class DoctorAvailabilityControllerTest {
    @Autowired
    private DoctorAvailabilityController doctorAvailabilityController;

    @MockBean
    private DoctorAvailabilityService doctorAvailabilityService;

    @Test
    void testSaveObject() throws Exception {
        doNothing().when(this.doctorAvailabilityService).saveAvailability((DoctorAvailabilityModel) any());

        DoctorAvailabilityModel doctorAvailabilityModel = new DoctorAvailabilityModel();
        doctorAvailabilityModel.setAvailableDate(new ArrayList<>());
        doctorAvailabilityModel.setDoctorId(123);
        doctorAvailabilityModel.setEndTime(new ArrayList<>());
        doctorAvailabilityModel.setStartTime(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(doctorAvailabilityModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doctor-availability")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.doctorAvailabilityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/doctorhomepage"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/doctorhomepage"));
    }

    @Test
    void testShowDcotorAvailability() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor-availability");
        MockMvcBuilders.standaloneSetup(this.doctorAvailabilityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctoravailabilitymodel"))
                .andExpect(MockMvcResultMatchers.view().name("doctoravailability"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctoravailability"));
    }

    @Test
    void testShowDcotorAvailability2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/doctor-availability");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.doctorAvailabilityController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctoravailabilitymodel"))
                .andExpect(MockMvcResultMatchers.view().name("doctoravailability"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctoravailability"));
    }
}

