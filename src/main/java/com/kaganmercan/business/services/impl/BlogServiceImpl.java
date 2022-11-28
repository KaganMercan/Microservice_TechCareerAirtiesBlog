package com.kaganmercan.business.services.impl;

import com.kaganmercan.bean.ModelMapperBean;
import com.kaganmercan.business.dto.BlogDto;
import com.kaganmercan.business.services.IBlogServices;
import com.kaganmercan.data.entity.BlogEntity;
import com.kaganmercan.data.repository.IBlogRepository;
import com.kaganmercan.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaganmercan
 */
// lombok
@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class BlogServiceImpl implements IBlogServices {
    private final IBlogRepository blogRepository;
    private final ModelMapperBean modelMapperBean;

    // Model Mapper -> Entity-Dto Conversion.
    // This Dto to Entity conversion or vice versa should be in SERVICE layer.
    // Dto's interaction with controller and Entity interaction with Repository.
    @Override
    public BlogDto entityToDto(BlogEntity blogEntity) {
        return modelMapperBean.modelMapperMethod().map(blogEntity, BlogDto.class);
    }

    @Override
    public BlogEntity dtoToEntity(BlogDto blogDto) {
        return modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
    }

    // CREATE
    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        BlogEntity blogEntity = dtoToEntity(blogDto);
        blogRepository.save(blogEntity);
        return blogDto;
    }

    // LIST
    @Override
    public List<BlogDto> listBlog() {
        List<BlogEntity> blogEntityList = blogRepository.findAll();
        List<BlogDto> blogDtoList = new ArrayList<>();
        for (BlogEntity temp : blogEntityList) {
            BlogDto blogDto = entityToDto(temp);
            blogDtoList.add(blogDto);
        }
        return blogDtoList;
    }

    // FIND
    @Override
    public BlogDto findBlog(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id + "ID not found."));
        return entityToDto(blogEntity);
    }

    // UPDATE
    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        BlogEntity blogEntity = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id + "ID not found."));
        if (blogEntity != null) {
            blogEntity.setBlogHeader(blogDto.getBlogHeader());
            blogEntity.setBlogContent(blogDto.getBlogContent());
            blogEntity.setBlogImage(blogDto.getBlogImage());
            blogRepository.save(blogEntity);
        }
        return blogDto;
    }

    // DELETE
    @Override
    public Map<String, Boolean> deleteBlog(Long id) {
        BlogEntity blogEntity = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id + "ID not found."));
        blogRepository.delete(blogEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted.", Boolean.TRUE);
        return response;
    }
}
