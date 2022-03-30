package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name = "bank")
@NoArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "city", length = 255, nullable = false)
    private String city;

    @Column(name = "state", length = 255, nullable = false)
    private String state;

    @Column(name = "zipcode", length = 6, nullable = false)
    private String zipcode;

    @Column(name = "full_name", length = 45, nullable = false)
    private String fullName;

    @Column(name = "card_number", length = 20, nullable = false)
    private String card_number;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "expiry_month", nullable = false)
    private int expiryMonth;

    @Column(name = "expiry_year", nullable = false)
    private int expiryYear;

    @Column(name = "cvv", nullable = false)
    private int cvv;

    public Bank(String email,String address, String city, String state, String zipcode,
                String fullName, String cardNumber,int expiryMonth, int expiryYear, int cvv,float amount){
        this.email=email;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zipcode=zipcode;
        this.fullName=fullName;
        this.card_number=cardNumber;
        this.expiryMonth=expiryMonth;
        this.expiryYear=expiryYear;
        this.cvv=cvv;
        this.amount=amount;
    }

}
