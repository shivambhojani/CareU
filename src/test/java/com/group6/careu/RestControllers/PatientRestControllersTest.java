package com.group6.careu.RestControllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.careu.entity.Appointment;
import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.Patient;
import com.group6.careu.entity.User;
import com.group6.careu.model.PatientSettingsModel;
import com.group6.careu.repository.AppointmentRepository;
import com.group6.careu.repository.PatientRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.service.AppointmentServiceImpl;
import com.group6.careu.service.PatientServiceImpl;

import java.sql.Date;
import java.sql.Time;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PatientRestControllers.class, AppointmentServiceImpl.class, PatientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PatientRestControllersTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientRestControllers patientRestControllers;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testGetTodaysAppointmentByPatientId() throws Exception {
        when(this.appointmentRepository.getTodaysAppointmentByPatientId((Integer) any(), (java.sql.Date) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getTodaysAppointmentbyPatientId/{PatientId}", 123);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetTodaysAppointmentByPatientId2() throws Exception {
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
        when(this.userRepository.getUserByDoctorId(anyInt())).thenReturn(user);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        Doctor doctor1 = new Doctor();
        doctor1.setDoctorAge(1);
        doctor1.setDoctorExperience("?");
        doctor1.setDoctorLocation("?");
        doctor1.setDoctorOverview("?");
        doctor1.setDoctorQualification("?");
        doctor1.setDoctorRegNumber("42");
        doctor1.setDoctorSpel("?");
        doctor1.setDoctor_id(1);
        Time time = mock(Time.class);
        when(time.getTime()).thenReturn(10L);

        Patient patient1 = new Patient();
        patient1.setDisease("?");
        patient1.setPatient_id(1);
        Time time1 = mock(Time.class);
        when(time1.getTime()).thenReturn(10L);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(date);
        appointment.setConsulationType("?");
        appointment.setDoctor(doctor1);
        appointment.setEndTime(time);
        appointment.setMedications("?");
        appointment.setPatient(patient1);
        appointment.setPatientFeedback("?");
        appointment.setStartTime(time1);

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        when(this.appointmentRepository.getTodaysAppointmentByPatientId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getTodaysAppointmentbyPatientId/{PatientId}", 123);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"appointment_id\":null,\"doctorContact\":null,\"doctorName\":\"Jane Doe\",\"patient_id\":1,\"date\":10,\"start"
                                        + "_time\":10,\"end_time\":10,\"consultationType\":\"?\",\"medications\":\"?\",\"patientFeedback\":null}]"));
    }

    @Test
    void testGetFutureAppointmentByPatientId() throws Exception {
        when(this.appointmentRepository.getFutureAppointmentByPatientId((Integer) any(), (java.sql.Date) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getFutureAppointmentbyPatientId/{PatientId}", 123);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetFutureAppointmentByPatientId2() throws Exception {
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
        when(this.userRepository.getUserByDoctorId(anyInt())).thenReturn(user);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        Doctor doctor1 = new Doctor();
        doctor1.setDoctorAge(1);
        doctor1.setDoctorExperience("?");
        doctor1.setDoctorLocation("?");
        doctor1.setDoctorOverview("?");
        doctor1.setDoctorQualification("?");
        doctor1.setDoctorRegNumber("42");
        doctor1.setDoctorSpel("?");
        doctor1.setDoctor_id(1);
        Time time = mock(Time.class);
        when(time.getTime()).thenReturn(10L);

        Patient patient1 = new Patient();
        patient1.setDisease("?");
        patient1.setPatient_id(1);
        Time time1 = mock(Time.class);
        when(time1.getTime()).thenReturn(10L);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(date);
        appointment.setConsulationType("?");
        appointment.setDoctor(doctor1);
        appointment.setEndTime(time);
        appointment.setMedications("?");
        appointment.setPatient(patient1);
        appointment.setPatientFeedback("?");
        appointment.setStartTime(time1);

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        when(this.appointmentRepository.getFutureAppointmentByPatientId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getFutureAppointmentbyPatientId/{PatientId}", 123);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"appointment_id\":null,\"doctorContact\":null,\"doctorName\":\"Jane Doe\",\"patient_id\":1,\"date\":10,\"start"
                                        + "_time\":10,\"end_time\":10,\"consultationType\":\"?\",\"medications\":\"?\",\"patientFeedback\":null}]"));
    }

    @Test
    void testGetPastAppointmentByPatientId() throws Exception {
        when(this.appointmentRepository.getPastAppointmentByPatientId((Integer) any(), (java.sql.Date) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getPastAppointmentbyPatientId/{PatientId}", 123);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPastAppointmentByPatientId2() throws Exception {
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
        when(this.userRepository.getUserByDoctorId(anyInt())).thenReturn(user);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        Doctor doctor1 = new Doctor();
        doctor1.setDoctorAge(1);
        doctor1.setDoctorExperience("?");
        doctor1.setDoctorLocation("?");
        doctor1.setDoctorOverview("?");
        doctor1.setDoctorQualification("?");
        doctor1.setDoctorRegNumber("42");
        doctor1.setDoctorSpel("?");
        doctor1.setDoctor_id(1);
        Time time = mock(Time.class);
        when(time.getTime()).thenReturn(10L);

        Patient patient1 = new Patient();
        patient1.setDisease("?");
        patient1.setPatient_id(1);
        Time time1 = mock(Time.class);
        when(time1.getTime()).thenReturn(10L);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(UUID.randomUUID());
        appointment.setAppointment_date(date);
        appointment.setConsulationType("?");
        appointment.setDoctor(doctor1);
        appointment.setEndTime(time);
        appointment.setMedications("?");
        appointment.setPatient(patient1);
        appointment.setPatientFeedback("?");
        appointment.setStartTime(time1);

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        when(this.appointmentRepository.getPastAppointmentByPatientId((Integer) any(), (Date) any()))
                .thenReturn(appointmentList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getPastAppointmentbyPatientId/{PatientId}", 123);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"appointment_id\":null,\"doctorContact\":null,\"doctorName\":\"Jane Doe\",\"patient_id\":1,\"date\":10,\"start"
                                        + "_time\":10,\"end_time\":10,\"consultationType\":\"?\",\"medications\":\"?\",\"patientFeedback\":null}]"));
    }

    @Test
    void testGetAllPatientsRest() throws Exception {
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
        when(this.patientRepository.findPatient((Integer) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patienthomepage/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"email\":\"jane.doe@example.org\",\"phone\""
                                        + ":\"4105551212\",\"password\":\"44556\",\"enabled\":true,\"role\":null,\"resetPasswordToken\":\"ABC123\",\"doctor"
                                        + "\":{\"doctor_id\":1,\"doctorSpel\":\"Doctor Spel\",\"doctorAge\":1,\"doctorOverview\":\"Doctor Overview\","
                                        + "\"doctorRegNumber\":\"42\",\"doctorExperience\":\"Doctor Experience\",\"doctorQualification\":\"Doctor"
                                        + " Qualification\",\"doctorLocation\":\"Doctor Location\"},\"patient\":{\"patient_id\":1,\"disease\":\"Disease\"}}"));
    }


    @Test
    void testGetAllPatientsRest2() throws Exception {
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
        when(this.patientRepository.findPatient((Integer) any())).thenReturn(user);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/patienthomepage/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"email\":\"jane.doe@example.org\",\"phone\""
                                        + ":\"4105551212\",\"password\":\"44556\",\"enabled\":true,\"role\":null,\"resetPasswordToken\":\"ABC123\",\"doctor"
                                        + "\":{\"doctor_id\":1,\"doctorSpel\":\"Doctor Spel\",\"doctorAge\":1,\"doctorOverview\":\"Doctor Overview\","
                                        + "\"doctorRegNumber\":\"42\",\"doctorExperience\":\"Doctor Experience\",\"doctorQualification\":\"Doctor"
                                        + " Qualification\",\"doctorLocation\":\"Doctor Location\"},\"patient\":{\"patient_id\":1,\"disease\":\"Disease\"}}"));
    }

    @Test
    void testUpdatePatient() throws Exception {
        when(this.patientRepository.updatePatientData((Integer) any(), (String) any(), (String) any(), (String) any(),
                (String) any())).thenReturn(1);
        when(this.patientRepository.updatePatientDatainPatient((Integer) any(), (String) any())).thenReturn(1);

        PatientSettingsModel patientSettingsModel = new PatientSettingsModel();
        patientSettingsModel.setDisease("Disease");
        patientSettingsModel.setEmail("jane.doe@example.org");
        patientSettingsModel.setFirstName("Jane");
        patientSettingsModel.setGender("Gender");
        patientSettingsModel.setLastName("Doe");
        patientSettingsModel.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(patientSettingsModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatePatient/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    void testUpdatePatient2() throws Exception {
        when(this.patientRepository.updatePatientData((Integer) any(), (String) any(), (String) any(), (String) any(),
                (String) any())).thenReturn(0);
        when(this.patientRepository.updatePatientDatainPatient((Integer) any(), (String) any())).thenReturn(1);

        PatientSettingsModel patientSettingsModel = new PatientSettingsModel();
        patientSettingsModel.setDisease("Disease");
        patientSettingsModel.setEmail("jane.doe@example.org");
        patientSettingsModel.setFirstName("Jane");
        patientSettingsModel.setGender("Gender");
        patientSettingsModel.setLastName("Doe");
        patientSettingsModel.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(patientSettingsModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatePatient/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    @Test
    void testUpdatePatient3() throws Exception {
        when(this.patientRepository.updatePatientData((Integer) any(), (String) any(), (String) any(), (String) any(),
                (String) any())).thenReturn(1);
        when(this.patientRepository.updatePatientDatainPatient((Integer) any(), (String) any())).thenReturn(0);

        PatientSettingsModel patientSettingsModel = new PatientSettingsModel();
        patientSettingsModel.setDisease("Disease");
        patientSettingsModel.setEmail("jane.doe@example.org");
        patientSettingsModel.setFirstName("Jane");
        patientSettingsModel.setGender("Gender");
        patientSettingsModel.setLastName("Doe");
        patientSettingsModel.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(patientSettingsModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatePatient/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.patientRestControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }
}

