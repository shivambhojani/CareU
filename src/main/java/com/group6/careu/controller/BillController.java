package com.group6.careu.controller;

import com.group6.careu.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/bill/{id}/{bankReferenceCode}")
    public String showBill(Model model,@PathVariable("id") Integer id,@PathVariable("bankReferenceCode") Integer bankReferenceCode){
        billService.billProcessor(id,bankReferenceCode);
        return "bill";
    }
}
