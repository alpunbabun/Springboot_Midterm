package com.example.Springboot.Service;

import com.example.Springboot.Entity.Customer;
import com.example.Springboot.Web.CustomerRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {
    Customer save(CustomerRegistrationDto registrationDto);
}
