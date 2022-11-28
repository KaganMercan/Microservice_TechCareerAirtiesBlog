package com.kaganmercan.ui.api;

import com.kaganmercan.business.dto.BlogDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author kaganmercan
 */
public interface IBlogApi {
    // CREATE
    ResponseEntity<?> createBlog(BlogDto blogDto);

    // LIST
    ResponseEntity<List<BlogDto>>  listBlog();

    // FIND
    ResponseEntity<BlogDto> findBlog(Long id);


    // UPDATE
    ResponseEntity<BlogDto>  updateBlog(Long id, BlogDto blogDto);

    // DELETE
    ResponseEntity <Map<String, Boolean>> deleteBlog(Long id);
}
