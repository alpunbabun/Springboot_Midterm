package com.example.Springboot.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

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
                customerRepository.save(customer);
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
                customerRepository.save(customer);
                return Status.SUCCESSFULLY_LOGOUT;
            }
        }        return Status.FAILURE;
    }

//    @DeleteMapping("/customers/delete")
//    public Status deleteCustomers(@Valid @RequestBody Customer customer) {
//        List<Customer> customers = customerRepository.findAll();
//        for (Customer other : customers) {
//            if (other.equals(customer)) {
//                customer.setLoggedIn(false);
//                customerRepository.delete(customer);
//                return Status.SUCCESSFULLY_DELETED;
//            }
//        }       return Status.FAILURE;
//    }

    @DeleteMapping("/customers/all")
    public Status deleteCustomers() {
        customerRepository.deleteAll();
        return Status.SUCCESSFULLY_DELETED;
    }
}
