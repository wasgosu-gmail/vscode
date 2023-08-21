package com.redstack.customer;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public record CustomerService(CustomerRepository repository) {

    public void registerCustomer(CustomerRegistrationRequest req) {
        Customer customer = Customer.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .build();
        repository.saveAndFlush(customer);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> getCustomer(Integer id) {
        return repository.findById(id);
    }
}