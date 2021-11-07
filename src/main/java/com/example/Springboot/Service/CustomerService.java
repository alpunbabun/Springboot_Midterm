package com.example.Springboot.Service;

import com.example.Springboot.Entity.Customer;
import com.example.Springboot.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }

    public void registerCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id" + id + "does not exists");
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Customer customer, Long id) {
        customerRepository.save(customer);
    }
}
