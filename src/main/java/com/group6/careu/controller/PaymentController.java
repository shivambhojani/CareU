package com.group6.careu.controller;

import com.group6.careu.model.TransactionsModel;
import com.group6.careu.service.BankService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@ToString
public class PaymentController {

    @Autowired
    private BankService bankService;

    @GetMapping("/payment-latest")
    public String showLandingPage(Model model) {
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "paymentlatest";
    }

    @PostMapping("/payment-latest")
    public String getPaymentAttributes(@ModelAttribute TransactionsModel transactionsModel,Model model){
        model.addAttribute("transactionsModel", transactionsModel);
        boolean status=bankService.processPayment(transactionsModel);
        if(status==false){
            return "payment_failure";
        }else{
            return "payment_success";
        }
    }
}
