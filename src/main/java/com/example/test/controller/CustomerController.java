package com.example.test.controller;

import com.example.test.model.AutoCode;
import com.example.test.model.Customer;
import com.example.test.model.EntityCode;
import com.example.test.model.User;
import com.example.test.service.base.AutoCodeService;
import com.example.test.service.base.CustomerService;
import com.example.test.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private AutoCodeService autoCodeService;

    @GetMapping("get")
    public ResponseEntity<?> findAllCustomer() {
        Iterable<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        AutoCode autoCode = autoCodeService.findById(1L).get();
        long codeNumber = autoCode.getCodeNumber();
        codeNumber++;
        autoCode.setCodeNumber(codeNumber);
        customer.setCode(EntityCode.autoCode("C", codeNumber));
        User user = userService.CreateUser(customer.getCode(), "Manager", "ROLE_CUSTOMER");
        customer.setUser(user);
        customer.setCreatedDate(LocalDateTime.now());
        customer.setUpdateDate(LocalDateTime.now());
        autoCodeService.save(autoCode);
        return ResponseEntity.ok(customerService.save(customer));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(customer.getId());
        customerOptional.ifPresent(value -> customer.setCode(value.getCode()));
        return ResponseEntity.ok(customerService.save(customer));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchByName/{username}")
    public ResponseEntity<?> searchCustomerLike(@PathVariable String username) {
        List<Customer> customers = (List<Customer>) customerService.findCustomerLike(username);
        if (customers.size() > 0) {
            return ResponseEntity.ok(customers);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchByDate/{date}")
    public ResponseEntity<?> searchCustomerByDate(@PathVariable String date) {
        List<Customer> customer = customerService.findByCreateDate(date);
        if (customer.size()>0){
            return ResponseEntity.ok(customer);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROlE_MANAGER'})")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            customerService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
