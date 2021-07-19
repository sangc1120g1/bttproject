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
public class Class extends CreateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private Collection<Teacher> teachers;
    @ManyToMany
    private Collection<Student> students;
    @ManyToMany
    private Collection<Manager> managers;
    @ManyToMany
    private Collection<Salesman> salesmen;
    private String typeOfClass;
    @NotNull
    private double duration;
    private String schedule;
    @ManyToMany
    private Collection<Book> books;
    @NotNull
    private int status;
    private String note;
}
