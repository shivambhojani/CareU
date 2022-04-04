package com.group6.careu.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelAppointmentModel {
    private String apptDate;
    private Integer doctorId;
    private Integer patientId;
}
