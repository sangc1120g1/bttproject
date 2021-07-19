package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.base.AutoCodeService;
import com.example.test.service.base.SalesmanService;
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
@RequestMapping("salesman")
public class SalesmanController {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private UserService userService;
    @Autowired
    private AutoCodeService autoCodeService;


    @GetMapping("get")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(salesmanService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<?> createSalesman(@RequestBody Salesman salesman) {
        AutoCode autoCode = autoCodeService.findById(1L).get();
        long codeNumber = autoCode.getCodeNumber();
        codeNumber++;
        autoCode.setCodeNumber(codeNumber);
        salesman.setCode(EntityCode.autoCode("SM", codeNumber));
        User user = userService.CreateUser(salesman.getCode(), "Salesman", "ROLE_CUSTOMER");
        salesman.setUser(user);
        salesman.setCreatedDate(LocalDateTime.now());
        salesman.setUpdateDate(LocalDateTime.now());
        autoCodeService.save(autoCode);
        return ResponseEntity.ok(salesmanService.save(salesman));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<?> editSalesman(@RequestBody Salesman salesman) {
        Optional<Salesman> customerOptional = salesmanService.findById(salesman.getId());
        customerOptional.ifPresent(value -> salesman.setCode(value.getCode()));
        return ResponseEntity.ok(salesmanService.save(salesman));
    }


    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchSalesman(@PathVariable Long id) {
        Optional<Salesman> salesman = salesmanService.findById(id);
        if (salesman.isPresent()) {
            return ResponseEntity.ok(salesman.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<?> searchSalesmanByName(@PathVariable String name) {
        List<Salesman> salesman = salesmanService.findByName(name);
        if (salesman.size()>0) {
            return ResponseEntity.ok(salesman);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/searchCreateDate/{date}")
    public ResponseEntity<?> searchSalesmanByCreateDate(@PathVariable String date) {
        List<Salesman> salesman = salesmanService.findByCreateDate(date);
        if (salesman.size()>0) {
            return ResponseEntity.ok(salesman);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<?> deleteSalesman(@PathVariable Long id) {
        Optional<Salesman> salesman = salesmanService.findById(id);
        if (salesman.isPresent()) {
            salesmanService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
