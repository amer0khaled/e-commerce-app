package com.amer.ecommerce.customer;

import com.amer.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public @Nullable String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(
            String customerId,
            CustomerRequest request
    ) {
        var customerToUpdate = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Cannot Update Customer:: No Customer Found With ID:: " + customerId ));

        mergeCustomer(customerToUpdate, request);
        customerRepository.save(customerToUpdate);
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

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public boolean existsById(String customerId) {
        return customerRepository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No Customer Found With ID:: " + customerId));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
