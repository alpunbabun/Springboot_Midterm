package com.example.Springboot.Web;

import com.example.Springboot.Service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class CustomerRegistrationController {
    private CustomerService customerService;

    public CustomerRegistrationController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @ModelAttribute("user")
    public CustomerRegistrationDto userRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") CustomerRegistrationDto registrationDto) {
        customerService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
