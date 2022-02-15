package com.example.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoNameController {

        @RequestMapping(path = "welcome")
        public String getMessage() {
                System.out.println("entered");
                return "Deployed from Master";
        }
}
