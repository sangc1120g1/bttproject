package com.example.test.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@MappedSuperclass
public abstract class CreateEntity implements Serializable {
    @CreatedDate
    @Column(name = "created_date")
    protected LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "update_date")
    protected LocalDateTime updateDate;
    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;
    @LastModifiedBy
    @Column(name = "updated_by")
    protected String updateBy;
}

