package com.kaganmercan.data.entity;

import com.kaganmercan.business.dto.BlogDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kaganmercan
 */

// lombok
@Getter
@Setter
// Super class
@MappedSuperclass

//TODO: Can auditing method replaced with username instead of given auditor? UPDATE HERE...
// Auditing
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "system_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date systemCreatedDate;

    // Auditing
    // Representing that, who created that data...
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    // Representation about data creation time...
    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    // Who updated the data...
    @Column(name="last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    // Who updated the data  at what time...
    @Column(name="last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;
}
