package com.example.Springboot.Controller;

import com.example.Springboot.Service.CustomerService;
import com.example.Springboot.CUSTOMER.Status;
import com.example.Springboot.Entity.Customer;
import com.example.Springboot.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(value = "/customers/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers/register")
    public Status registerCustomer(@Valid @RequestBody Customer newCustomer) {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("New customer: " + newCustomer.toString());

        for (Customer customer : customers) {
            System.out.println("Registered customer: " + newCustomer.toString());

            if (customer.equals(newCustomer)) {
                System.out.println("Customer Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        customerRepository.save(newCustomer);
        return Status.SUCCESSFULLY_REGISTERED;
    }

    @PostMapping("/customers/login")
    public Status loginCustomer(@Valid @RequestBody Customer customer) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer other : customers) {
            if (other.equals(customer)) {
                customer.setLoggedIn(true);
//                customerRepository.save(customer);
                return Status.SUCCESSFULLY_LOGIN;
            }
        }        return Status.FAILURE;
    }

    @PostMapping("/customers/logout")
    public Status logOutCustomer(@Valid @RequestBody Customer customer) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer other : customers) {
            if (other.equals(customer)) {
                customer.setLoggedIn(false);
//                customerRepository.save(customer);
                return Status.SUCCESSFULLY_LOGOUT;
            }
        }        return Status.FAILURE;
    }

    @PutMapping(value = "/customers/{id}")
    public Status updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        this.customerService.updateCustomer(customer, id);
//        return customer;
        return Status.SUCCESSFULLY_UPDATED;
    }

    @DeleteMapping(value = "/customers/{id}")
    public Status deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return Status.SUCCESSFULLY_DELETED;
    }

    @DeleteMapping("/customers/all")
    public Status deleteCustomers() {
        customerRepository.deleteAll();
        return Status.SUCCESSFULLY_DELETED;
    }
}
