package com.example.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Lesson extends CreateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Date day;
    @NotNull
    private String code;
    private String typeOfLesson;
    private double duration;
    @ManyToMany
    private Collection<Class> classes;
    private double teachFee;
    private double studyFee;
    private double managerFee;
    private double saleFee;
    @ManyToMany
    private Collection<Customer> customers;
    private String node;
}
