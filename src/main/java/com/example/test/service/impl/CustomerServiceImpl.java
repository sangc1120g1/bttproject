package com.example.test.service.impl;

import com.example.test.model.Customer;
import com.example.test.repository.CustomerRepository;
import com.example.test.service.base.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByCreateDate(String date) {
        date = "%"+date+"%";
        return customerRepository.findCustomerByCreatedDate(date);
    }


    @Override
    public Iterable<Customer> findCustomerLike(String username) {
        return customerRepository.findByNameContaining(username);
    }
}
