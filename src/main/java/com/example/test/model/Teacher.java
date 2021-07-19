package com.example.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Teacher extends CreateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @NotNull
    private String name;
    private String facebook;
    private String payment;
    @NotNull
    private int rate;
    @ManyToMany
    private Collection<Certificate> certificates;
    private String note;
    private int status;
    private double total;
    private double paid;
    private double balance;
    @OneToOne
    private User user;
}
