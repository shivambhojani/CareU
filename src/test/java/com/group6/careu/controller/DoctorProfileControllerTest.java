package com.group6.careu.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.User;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.DoctorService;
import com.group6.careu.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {DoctorProfileController.class, UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class DoctorProfileControllerTest {
    @Autowired
    private DoctorProfileController doctorProfileController;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetDoctorProfileAttributes() throws Exception {
        doNothing().when(this.doctorService).saveDoctorInformation((com.group6.careu.entity.Doctor) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doctorProfile");
        MockMvcBuilders.standaloneSetup(this.doctorProfileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/licenseUpload"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/licenseUpload"));
    }

    @Test
    void testGetDoctorProfileAttributes2() throws Exception {
        doNothing().when(this.doctorService).saveDoctorInformation((com.group6.careu.entity.Doctor) any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/doctorProfile");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.doctorProfileController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/licenseUpload"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/licenseUpload"));
    }

    @Test
    void testUploadLicenseUpload() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/licenseUpload");
        MockMvcBuilders.standaloneSetup(this.doctorProfileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("file"))
                .andExpect(MockMvcResultMatchers.view().name("doctorlicense"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorlicense"));
    }

    @Test
    void testUploadLicenseUpload2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/licenseUpload");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.doctorProfileController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("file"))
                .andExpect(MockMvcResultMatchers.view().name("doctorlicense"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorlicense"));
    }
}

