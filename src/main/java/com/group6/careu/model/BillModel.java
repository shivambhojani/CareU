package com.group6.careu.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

@Getter
@Setter
public class BillModel {

    PDDocument bill;

    public BillModel(){
        bill=new PDDocument();
        PDPage page = new PDPage();
        bill.addPage(page);
    }
    
}
