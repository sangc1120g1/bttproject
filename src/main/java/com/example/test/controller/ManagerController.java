package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.base.AutoCodeService;
import com.example.test.service.base.ManagerService;
import com.example.test.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private AutoCodeService autoCodeService;

    @Autowired
    private UserService userService;

    @GetMapping("get")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(managerService.findAll());
    }

    @GetMapping("get/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable("page") int page , @PathVariable("size") int size){
        PageRequest pageable = PageRequest.of(page , size);
        return ResponseEntity.ok( managerService.findAll((pageable)));
    }

    @PostMapping
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
        AutoCode autoCode = autoCodeService.findById(1L).get();
        long codeNumber = autoCode.getCodeNumber();
        codeNumber++;
        autoCode.setCodeNumber(codeNumber);
        manager.setCode(EntityCode.autoCode("M", codeNumber));
        User user = userService.CreateUser(manager.getCode(), "Manager", "ROLE_MANAGER");
        manager.setUser(user);
        manager.setCreatedDate(LocalDateTime.now());
        manager.setUpdateDate(LocalDateTime.now());
        autoCodeService.save(autoCode);
        return ResponseEntity.ok(managerService.save(manager));
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<Manager> editManager(@RequestBody Manager manager) {
        Optional<Manager> managerOptional = managerService.findById(manager.getId());
        managerOptional.ifPresent(value -> manager.setCode(value.getCode()));
        return ResponseEntity.ok(managerService.save(manager));
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchManager(@PathVariable Long id) {
        Optional<Manager> customer = managerService.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/username/{name}")
    public ResponseEntity<?> searchManagerByName(@PathVariable String  name) {
        List<Manager> customer = managerService.findByName(name);
        if (customer.size()>0) {
            return ResponseEntity.ok(customer);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/create/{date}")
    public ResponseEntity<?> searchManagerByDate(@PathVariable String  date) {
        List<Manager> customer = managerService.findByCreateDate(date);
        if (customer.size()>0) {
            return ResponseEntity.ok(customer);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @DeleteMapping("{id}")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Manager> customer = managerService.findById(id);
        if (customer.isPresent()) {
            managerService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
