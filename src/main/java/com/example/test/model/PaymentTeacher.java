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
public class PaymentTeacher extends CreateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToMany
    private Collection<Teacher> teachers;
    private double amountUsd;
    private double transferUsd;
    private double totalUsd;
    private double amountVnd;
    private double transferVnd;
    private double totalVnd;
    @NotNull
    private double amountPhp;
    private String note;
}
