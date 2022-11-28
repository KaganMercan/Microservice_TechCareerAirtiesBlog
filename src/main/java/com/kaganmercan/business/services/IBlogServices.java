package com.kaganmercan.business.services;

import com.kaganmercan.business.dto.BlogDto;
import com.kaganmercan.data.entity.BlogEntity;

import java.util.List;
import java.util.Map;

/**
 * @author kaganmercan
 */
public interface IBlogServices {
    BlogDto entityToDto(BlogEntity blogEntity);

    BlogEntity dtoToEntity(BlogDto blogDto);

    // CREATE
    BlogDto createBlog(BlogDto blogDto);

    // LIST
    List<BlogDto> listBlog();

    // FIND
    BlogDto findBlog(Long id);

    // UPDATE
    BlogDto updateBlog(Long id, BlogDto blogDto);

    // DELETE
    Map<String, Boolean> deleteBlog(Long id);


}
