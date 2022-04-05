package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@ToString
@Table(name = "transactions")
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

    /*
    @Column(length = 128, nullable = false)
    private String email;
       */

    @Column(name = "card_number", length = 20, nullable = false)
    private String cardNumber;

    /*
    @Column(name = "expiry_month", nullable = false)
    private Integer expiryMonth;

    @Column(name = "expiry_year", nullable = false)
    private Integer expiryYear;

    @Column(name = "cvv", nullable = false)
    private Integer cvv;
     */

    @Column(name = "full_name", length = 45, nullable = false)
    private String fullName;

    /*
    @Column(name = "country", length = 255, nullable = false)
    private String country;

    @Column(name = "zipcode", length = 6, nullable = false)
    private String zipcode;
    */

    @Column(name = "card_type", length = 20, nullable = false)
    private String cardType;

    @Column(name = "transaction_status", length = 20, nullable = true)
    private String transactionStatus;

    @Column(name = "bank_reference_code", length = 20, nullable = true)
    private Integer bankReferenceCode;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "status_code", nullable = true)
    private Integer statusCode;

    @Column(name = "status_description", length = 255, nullable = true)
    private String statusDescription;

    @CreationTimestamp
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;
    */
    // We need to get user_id and store as primary key

    public Transactions(
                String fullName, String cardNumber,String cardType,String transactionStatus,
                        Integer bankReferenceCode,Integer statusCode,String statusDescription,
                        float amount,Timestamp createdOn,
                        Timestamp updatedOn){
        //this.email=email;
        //this.country=country;
        //this.zipcode=zipcode;
        this.fullName=fullName;
        this.cardNumber=cardNumber;
        this.cardType=cardType;
        this.transactionStatus=transactionStatus;
        this.bankReferenceCode=bankReferenceCode;
        this.statusCode=statusCode;
        this.statusDescription=statusDescription;
        //this.expiryMonth=expiryMonth;
        //this.expiryYear=expiryYear;
        //this.cvv=cvv;
        this.amount=amount;
        this.createdOn=createdOn;
        this.updatedOn=updatedOn;
    }

}
