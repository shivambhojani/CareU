package com.group6.careu.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.exceptions.UserNotFoundException;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.service.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
/**
 * Created by Bijitashya on 03, 2022
 */
@ContextConfiguration(classes = {ForgotPasswordController.class, UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class ForgotPasswordControllerTest {
    @Autowired
    private ForgotPasswordController forgotPasswordController;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserServiceImpl userServiceImpl;


    @Test
    void processRequestForm() throws Exception {
        when(this.userServiceImpl.updateResetPasswordToken((String) any())).thenReturn("2020-03-01");
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void processRequestForm2() throws Exception {
        when(this.userServiceImpl.updateResetPasswordToken((String) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/forgot_password");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.view().name("forgot_password_form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("forgot_password_form"));
    }

    @Test
    void processRequestForm3() throws Exception {
        when(this.userServiceImpl.updateResetPasswordToken((String) any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/forgot_password");
        postResult.contentType("https://abc.org/example");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.view().name("forgot_password_form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("forgot_password_form"));
    }
    @Test
    void testProcessResetForm() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);

        Patient patient = new Patient();
        patient.setDisease("Disease");
        patient.setPatient_id(1);

        User user = new User();
        user.setDoctor(doctor);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setId(1);
        user.setLastName("Doe");
        user.setPassword("45587");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");

        Doctor doctor1 = new Doctor();
        doctor1.setDoctorAge(1);
        doctor1.setDoctorExperience("Doctor Experience");
        doctor1.setDoctorLocation("Doctor Location");
        doctor1.setDoctorOverview("Doctor Overview");
        doctor1.setDoctorQualification("Doctor Qualification");
        doctor1.setDoctorRegNumber("42");
        doctor1.setDoctorSpel("Doctor Spel");
        doctor1.setDoctor_id(1);

        Patient patient1 = new Patient();
        patient1.setDisease("Disease");
        patient1.setPatient_id(1);

        User user1 = new User();
        user1.setDoctor(doctor1);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setFirstName("Jane");
        user1.setGender("Gender");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPassword("44556");
        user1.setPatient(patient1);
        user1.setPhone("4105551212");
        user1.setResetPasswordToken("ABC123");
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findByResetPasswordToken((String) any())).thenReturn(user);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reset_password");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"))
                .andExpect(MockMvcResultMatchers.view().name("message"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("message"));
    }

    @Test
    void testShowRequestForm() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/forgot_password");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("forgot_password_form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("forgot_password_form"));
    }

    @Test
    void testShowRequestForm2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/forgot_password");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("forgot_password_form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("forgot_password_form"));
    }

    @Test
    void testShowResetForm() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);

        Patient patient = new Patient();
        patient.setDisease("Disease");
        patient.setPatient_id(1);

        User user = new User();
        user.setDoctor(doctor);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setId(1);
        user.setLastName("Doe");
        user.setPassword("44556");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.userServiceImpl.getByResetPasswordToken("ABC123")).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reset_password").param("token", "foo");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.view().name("message"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("message"));
    }

    @Test
    void testShowResetForm2() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setDoctorAge(1);
        doctor.setDoctorExperience("Doctor Experience");
        doctor.setDoctorLocation("Doctor Location");
        doctor.setDoctorOverview("Doctor Overview");
        doctor.setDoctorQualification("Doctor Qualification");
        doctor.setDoctorRegNumber("42");
        doctor.setDoctorSpel("Doctor Spel");
        doctor.setDoctor_id(1);

        Patient patient = new Patient();
        patient.setDisease("Disease");
        patient.setPatient_id(1);

        User user = new User();
        user.setDoctor(doctor);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setId(1);
        user.setLastName("Doe");
        user.setPassword("445589");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.userRepository.findByResetPasswordToken((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/reset_password");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("token", "foo");
        MockMvcBuilders.standaloneSetup(this.forgotPasswordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.view().name("message"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("message"));
    }
}

