package com.example.test.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Student extends CreateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @NotNull
    @Size(min = 1 , max = 50 , message = "độ dài từ 1 - 50 ")
    private String name;
    private String job;
    private Date birth;
    private String note;
    @ManyToMany
    private Collection<Customer> customers;
    private int status;
    @OneToOne
    private User user;
}
