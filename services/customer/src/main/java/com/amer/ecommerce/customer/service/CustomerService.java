package com.amer.ecommerce.customer.service;

import com.amer.ecommerce.customer.api.dto.CustomerRequest;
import com.amer.ecommerce.customer.api.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse updateCustomer(String customerId, CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);

}
