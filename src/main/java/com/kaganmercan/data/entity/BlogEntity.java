package com.kaganmercan.data.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * @author kaganmercan
 */
// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Entity - Table relation
@Entity
@Table(name = "blog")
public class BlogEntity extends BaseEntity implements Serializable {

    // blogHeader
    private String blogHeader;
    // blogContent
    private String blogContent;
    private String blogImage;
}
