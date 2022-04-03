package com.group6.careu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.repository.PatientRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import com.group6.careu.service.AppointmentService;
import com.group6.careu.service.AppointmentServiceImpl;
import com.group6.careu.service.DoctorAvailabilityService;
import com.group6.careu.service.DoctorService;
import com.group6.careu.service.EmailService;
import com.group6.careu.service.PatientServiceImpl;
import com.group6.careu.service.UserServiceImpl;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;

import java.util.ArrayList;
import java.util.UUID;
import javax.mail.MessagingException;

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
/**
 * Created by Bijitashya on 03, 2022
 */
@ContextConfiguration(classes = {AppointmentController.class, PatientServiceImpl.class, UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class AppointmentControllerTest {
    @Autowired
    private AppointmentController appointmentController;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private DoctorAvailabilityService doctorAvailabilityService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testBookAnAppointment() throws Exception {
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
        user.setPassword("44589");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.doctorService.getUserByDoctor((Integer) any())).thenReturn(user);
        when(this.doctorAvailabilityService.getAllTimes((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/searchDoctors/{doctor_id}", 1);
        MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("appointment", "availTimes", "selectedDoctor"))
                .andExpect(MockMvcResultMatchers.view().name("bookappointment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("bookappointment"));
    }

    @Test
    void testGetFutureAppointments() {
        when(this.appointmentService.getPatientFutureAppointments((Integer) any(), (Date) any()))
                .thenReturn(new ArrayList<>());

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
        user.setPassword("44589");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        assertTrue(this.appointmentController.getFutureAppointments(user, 123, mock(Date.class)).isEmpty());
        verify(this.appointmentService).getPatientFutureAppointments((Integer) any(), (Date) any());
    }

    @Test
    void testGetFutureAppointments2() {
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
        user.setPassword("789655");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.userRepository.getUserByDoctorId(anyInt())).thenReturn(user);

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

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(mock(Date.class));
        appointment.setConsulationType("Consulation Type");
        appointment.setDoctor(doctor1);
        appointment.setEndTime(mock(Time.class));
        appointment.setMedications("Medications");
        appointment.setPatient(patient1);
        appointment.setPatientFeedback("Patient Feedback");
        appointment.setStartTime(mock(Time.class));

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        when(this.appointmentService.getPatientFutureAppointments((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);

        Doctor doctor2 = new Doctor();
        doctor2.setDoctorAge(1);
        doctor2.setDoctorExperience("Doctor Experience");
        doctor2.setDoctorLocation("Doctor Location");
        doctor2.setDoctorOverview("Doctor Overview");
        doctor2.setDoctorQualification("Doctor Qualification");
        doctor2.setDoctorRegNumber("42");
        doctor2.setDoctorSpel("Doctor Spel");
        doctor2.setDoctor_id(1);

        Patient patient2 = new Patient();
        patient2.setDisease("Disease");
        patient2.setPatient_id(1);

        User user1 = new User();
        user1.setDoctor(doctor2);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setFirstName("Jane");
        user1.setGender("Gender");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPassword("77896");
        user1.setPatient(patient2);
        user1.setPhone("4105551212");
        user1.setResetPasswordToken("ABC123");
        assertEquals(1, this.appointmentController.getFutureAppointments(user1, 123, mock(Date.class)).size());
        verify(this.userRepository).getUserByDoctorId(anyInt());
        verify(this.appointmentService).getPatientFutureAppointments((Integer) any(), (Date) any());
    }

    @Test
    void testFetchAppointmentDetails() {
        assertTrue(this.appointmentController.fetchAppointmentDetails(new ArrayList<>()).isEmpty());
    }

    @Test
    void testFetchAppointmentDetails2() {
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
        user.setPassword("78996");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.userRepository.getUserByDoctorId(anyInt())).thenReturn(user);

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

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(mock(Date.class));
        appointment.setConsulationType("Consulation Type");
        appointment.setDoctor(doctor1);
        appointment.setEndTime(mock(Time.class));
        appointment.setMedications("Medications");
        appointment.setPatient(patient1);
        appointment.setPatientFeedback("Patient Feedback");
        appointment.setStartTime(mock(Time.class));

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        assertEquals(1, this.appointmentController.fetchAppointmentDetails(appointmentList).size());
        verify(this.userRepository).getUserByDoctorId(anyInt());
    }

    @Test
    void testBookAnAppointment2() throws Exception {
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
        user.setPassword("78966");
        user.setPatient(patient);
        user.setPhone("4105551212");
        user.setResetPasswordToken("ABC123");
        when(this.doctorService.getUserByDoctor((Integer) any())).thenReturn(user);
        when(this.doctorAvailabilityService.getAllTimes((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/searchDoctors/{doctor_id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("appointment", "availTimes", "selectedDoctor"))
                .andExpect(MockMvcResultMatchers.view().name("bookappointment"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("bookappointment"));
    }

    @Test
    void testCancelCurrentAppointmentOfUser() throws Exception {
        doNothing().when(this.doctorAvailabilityService)
                .updateBookedAppointment((com.group6.careu.model.AppointmentModel) any(), anyBoolean());

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

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(mock(Date.class));
        appointment.setConsulationType("Consulation Type");
        appointment.setDoctor(doctor);
        appointment.setEndTime(mock(Time.class));
        appointment.setMedications("Medications");
        appointment.setPatient(patient);
        appointment.setPatientFeedback("Patient Feedback");
        appointment.setStartTime(mock(Time.class));
        when(this.appointmentService.deleteAppointmentBasedOnId((String) any())).thenReturn(appointment);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAllDoctors() throws Exception {
        when(this.doctorService.getFilteredDoctor((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/searchDoctors").param("keyword", "foo");
        MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctorList"))
                .andExpect(MockMvcResultMatchers.view().name("doctorlist"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorlist"));
    }

    @Test
    void testGetAllDoctors2() throws Exception {
        when(this.doctorService.getAllDoctor()).thenReturn(new ArrayList<>());
        when(this.doctorService.getFilteredDoctor((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/searchDoctors").param("keyword", "");
        MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctorList"))
                .andExpect(MockMvcResultMatchers.view().name("doctorlist"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorlist"));
    }

    @Test
    void testGetAllDoctors3() throws Exception {
        when(this.doctorService.getAllDoctor()).thenReturn(new ArrayList<>());
        when(this.doctorService.getFilteredDoctor((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/searchDoctors").param("keyword", "");
        MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctorList"))
                .andExpect(MockMvcResultMatchers.view().name("doctorlist"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorlist"));
    }

    @Test
    void testGetAllDoctors4() throws Exception {
        when(this.doctorService.getAllDoctor()).thenReturn(new ArrayList<>());
        when(this.doctorService.getFilteredDoctor((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/searchDoctors");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("keyword", "null");
        MockMvcBuilders.standaloneSetup(this.appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctorList"))
                .andExpect(MockMvcResultMatchers.view().name("doctorlist"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("doctorlist"));
    }
}

