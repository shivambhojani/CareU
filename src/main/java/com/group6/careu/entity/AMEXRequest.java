package com.group6.careu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@ToString
@Table(name = "amex_request")
@NoArgsConstructor
public class AMEXRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankTransactionId;

    @Column(name = "card_number", length = 20, nullable = false)
    private String cardNumber;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "cvv", nullable = false)
    private Integer cvv;

    @Column(name = "card_type", length = 20, nullable = false)
    private String cardType;

    @CreationTimestamp
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;

    @Column(name = "creditor_name", length = 255, nullable = false)
    private String creditorName;

    @Column(name = "creditor_id", length = 10, nullable = false)
    private String creditorId;

    public AMEXRequest(Integer bankTransactionId, String cardNumber, float amount, Integer cvv,
                       String cardType, Timestamp createdOn, Timestamp updatedOn) {
        this.bankTransactionId = bankTransactionId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.cvv = cvv;
        this.cardType = cardType;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
}
