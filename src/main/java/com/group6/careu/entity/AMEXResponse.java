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
@NoArgsConstructor
@Table(name = "amex_response")
public class AMEXResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responseId;

    @Column(name = "status_code", nullable = false)
    private Integer statusCode;

    @Column(name = "status_description", length = 255, nullable = true)
    private String statusDescription;

    @Column(name = "transaction_id", nullable = false)
    private Integer transactionId;

    @CreationTimestamp
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;

    public AMEXResponse(Integer responseId, Integer statusCode, String statusDescription, Integer transactionId,
                        Timestamp createdOn, Timestamp updatedOn) {
        this.responseId = responseId;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.transactionId = transactionId;
        this.createdOn=createdOn;
        this.updatedOn=updatedOn;
    }
}
