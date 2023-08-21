package com.redstack.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// add these imports
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

// add these imports
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/customers")  //URL을 지정해주는 Annotation
public record CustomerController(CustomerService service) {

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrationRequest req) {
        service.registerCustomer(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully.\n");
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        Optional<Customer> c = service.getCustomer(id);
        if (c.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(c.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}