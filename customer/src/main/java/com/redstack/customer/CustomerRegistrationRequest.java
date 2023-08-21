package com.redstack.customer;

public record  CustomerRegistrationRequest (
    String firstName,
    String lastName,
    String email) {
}
