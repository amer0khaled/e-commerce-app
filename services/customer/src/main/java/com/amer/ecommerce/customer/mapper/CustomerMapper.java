package com.amer.ecommerce.customer.mapper;

import com.amer.ecommerce.customer.api.dto.CustomerRequest;
import com.amer.ecommerce.customer.api.dto.CustomerResponse;
import com.amer.ecommerce.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

}
