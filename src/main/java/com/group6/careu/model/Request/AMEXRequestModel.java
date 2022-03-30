package com.group6.careu.model.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AMEXRequestModel extends GenericRequestModel {
    private String creditorName;
    private String creditorId;

}
