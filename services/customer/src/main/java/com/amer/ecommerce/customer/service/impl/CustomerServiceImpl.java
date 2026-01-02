package com.amer.ecommerce.customer.service.impl;

import com.amer.ecommerce.customer.api.dto.CustomerRequest;
import com.amer.ecommerce.customer.api.dto.CustomerResponse;
import com.amer.ecommerce.customer.domain.Customer;
import com.amer.ecommerce.customer.mapper.CustomerMapper;
import com.amer.ecommerce.customer.repository.CustomerRepository;
import com.amer.ecommerce.customer.service.CustomerService;
import com.amer.ecommerce.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse updateCustomer(
            String customerId,
            CustomerRequest request
    ) {
        var customerToUpdate = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Cannot Update Customer:: No Customer Found With ID:: " + customerId ));

        mergeCustomer(customerToUpdate, request);
        var updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.fromCustomer(updatedCustomer);
    }


    private void mergeCustomer(Customer customerToUpdate,
                               CustomerRequest request
    ) {

        if (StringUtils.isNotBlank(request.firstName())) {
            customerToUpdate.setFirstName(request.firstName());
        }

        if (StringUtils.isNotBlank(request.lastName())) {
            customerToUpdate.setLastName(request.lastName());
        }

        if (StringUtils.isNotBlank(request.email())) {
            customerToUpdate.setEmail(request.email());
        }

        if (request.address() != null) {
            customerToUpdate.setAddress(request.address());
        }

    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    @Override
    public boolean existsById(String customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No Customer Found With ID:: " + customerId));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

}
