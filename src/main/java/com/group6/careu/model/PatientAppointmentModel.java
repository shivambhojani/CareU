package com.group6.careu.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAppointmentModel {
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID appointment_id;
    private String doctorContact;
    private String doctorEmail;
    private String doctorName;
    private Integer patient_id;
    private Date date;
    private Time start_time;
    private Time end_time;
    private String consultationType;
    private String medications;
    private String patientFeedback;


}
