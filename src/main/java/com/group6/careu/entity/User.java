package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "gender", length = 20, nullable = false)
    private String gender;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String phone;
    private String password;
    private boolean enabled;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id")
    @Fetch(FetchMode.JOIN)
    Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id")
    @Fetch(FetchMode.JOIN)
    Patient patient;

    public void setRole(String role) {
        this.role = role;
        if(this.role.equalsIgnoreCase("doctor")){
            this.doctor = new Doctor();
        } else{
            this.patient = new Patient();
        }
    }

    public User(String firstName, String lastName, String gender, String phone, String email, String password, boolean enabled, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

}
