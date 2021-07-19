package com.example.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class Customer extends CreateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @NotNull
    private String name;
    private String facebook;
    private String phone;
    private String email;
    @ManyToOne
    private Location location;
    @ManyToMany
    private Collection<Manager> managers;
    private String note;
    private double total;
    private double paid;
    private double balance;
    @OneToOne
    private User user;
}
