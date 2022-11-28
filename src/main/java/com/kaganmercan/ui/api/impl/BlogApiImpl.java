package com.kaganmercan.ui.api.impl;

import com.kaganmercan.business.dto.BlogDto;
import com.kaganmercan.business.services.IBlogServices;
import com.kaganmercan.ui.api.IBlogApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaganmercan
 */

// Lombok
@Log4j2
@RequiredArgsConstructor
// Rest
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
// We can query with these endpoints on postman and get response with those queries.
// Check our data on related database.
public class BlogApiImpl implements IBlogApi {
    // Injection of services
    private final IBlogServices services;

    // CREATE
    // http://localhost:3333/api/v1/blog/create
    @Override
    @PostMapping("blog/create")
    public ResponseEntity<?> createBlog(@Valid @RequestBody BlogDto blogDto) {
        services.createBlog(blogDto);
        return ResponseEntity.ok(blogDto);
    }

    // LIST
    // http://localhost:3333/api/v1/blog/list
    @Override
    @GetMapping("blog/list")
    public ResponseEntity<List<BlogDto>> listBlog() {
        List<BlogDto> blogList = services.listBlog();
        return ResponseEntity.ok(blogList);
    }

    // FIND
    // http://localhost:3333/api/v1/blog/find/1
    @Override
    @GetMapping("blog/find/{id}")
    public ResponseEntity<BlogDto> findBlog(@PathVariable(name = "id") Long id) {
        BlogDto findBlog = services.findBlog(id);
        return ResponseEntity.ok(findBlog);
    }

    // UPDATE
    // http://localhost:3333/api/v1/blog/update/1
    @Override
    @PutMapping("blog/update/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable(name = "id") Long id, @Valid @RequestBody BlogDto blogDto) {
        BlogDto updatedBlog = services.updateBlog(id, blogDto);
        return ResponseEntity.ok(updatedBlog);
    }

    // DELETE
    // http://localhost:3333/api/v1/blog/delete/1
    @Override
    @DeleteMapping("blog/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBlog(@PathVariable(name = "id") Long id) {
        services.deleteBlog(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Blog with id {} deleted" + id, Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
