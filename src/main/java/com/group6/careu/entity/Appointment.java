package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

/**
 * Created by Bijitashya on 03, 2022
 */

@Data
@Entity
@ToString
@Table(name = "appointment")
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(generator = "uuid")
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
    @Type(type="uuid-char")
    @Column(name = "appointment_id", columnDefinition = "CHAR(36)")
    private UUID appointmentId;

    @Lob
    private String medications;

    @ManyToOne
    @JoinColumn(name = "doctor_id", updatable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", updatable = false)
    private Patient patient;

    private String consulationType;

    //hh:mm:ss
    private Time startTime;

    private Time endTime;

    //YYYY-MM-DD
    private Date appointment_date;

    private String patientFeedback;

    public Appointment(String medications, Doctor doctor, Patient patient, String consulationType, Time startTime, Time endTime, Date appointment_date) {
        this.medications = medications;
        this.doctor = doctor;
        this.patient = patient;
        this.consulationType = consulationType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointment_date = appointment_date;
    }
}
