package com.kaganmercan.business.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kaganmercan
 */
// Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlogDto implements Serializable {
    // Blog Data
    // Blog post id...
    private Long id;
    // Blog post header...
    @NotEmpty(message = "{blog.header.validation.constraints.NotNull.message}")
    private String blogHeader;
    // Blog post content...
    @NotEmpty(message = "{blog.content.validation.constraints.NotNull.message}")
    private String blogContent;
    @NotEmpty(message = "{blog.content.validation.constraints.NotNull.message}")
    private String blogImage;
    // Blog post created date.
    private Date createdDate;
}
