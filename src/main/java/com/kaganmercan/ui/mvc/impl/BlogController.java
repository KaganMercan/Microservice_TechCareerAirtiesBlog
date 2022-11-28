package com.kaganmercan.ui.mvc.impl;

import com.kaganmercan.bean.ModelMapperBean;
import com.kaganmercan.business.dto.BlogDto;
import com.kaganmercan.data.entity.BlogEntity;
import com.kaganmercan.data.repository.IBlogRepository;
import com.kaganmercan.exception.ResourceNotFoundException;
import com.kaganmercan.ui.mvc.IBlogController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author kaganmercan
 */
// Lombok
@RequiredArgsConstructor
@Log4j2
// Spring Framework
@Controller
// Controller for Spring MVC(Frontend) Using Thymeleaf
public class BlogController implements IBlogController {
    private final IBlogRepository blogRepository;
    private final ModelMapperBean modelMapperBean;

    // SPEED CREATE
    // http://localhost:3333/speedData
    @Override
    @GetMapping("/speedData")
    public String createSpeedData(Model model) {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            UUID uuid = UUID.randomUUID();
            BlogEntity blogEntity = BlogEntity.builder()
                    .blogHeader("Header " + i)
                    .blogContent("Content " + i)
                    .build();
            blogRepository.save(blogEntity);
            counter++;
        }
        model.addAttribute("key_dataset", counter + " blog post entity created.");
        return "redirect:/blog/list";
    }

    // SPEED DELETE
    // http://localhost:3333/speedDelete
    @Override
    @GetMapping("/speedDelete")
    public String deleteSpeedData(Model model) {
        blogRepository.deleteAll();
        return "redirect:/blog/list";
    }
    // CREATE
    // http://localhost:3333/blog/create
    @Override
    @GetMapping("/blog/create")
    public String createGetBlog(Model model) {
        model.addAttribute("key_blog", new BlogDto());
        return "blog_create";
    }

    //CREATE
    // http://localhost:3333/blog/create
    @Override
    @PostMapping("/blog/create")
    public String createPostBlog(@Valid @ModelAttribute("key_blog") BlogDto blogDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR: " + bindingResult);
            return "blog_create";
        }
        // If no errors on binding
        model.addAttribute("blog_success", "Blog post successfully added." + blogDto);
        log.info("Success " + blogDto);
        // Model Mapper to convert dto to entity and do database operations.
        BlogEntity blogEntity = modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
        try {
            if (blogEntity != null) {
                blogRepository.save(blogEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //File
        return "success";
    }


    // http://localhost:3333/blog/list
    @Override
    @GetMapping("/blog/list")
    public String blogList(Model model) {
        List<BlogEntity> list = blogRepository.findAll();
        model.addAttribute("key_blog", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        return "blog_list";
    }

    // FIND
    // http://localhost:3333/blog/find
    // http://localhost:3333/blog/find/1
    @Override
    @GetMapping("/blog/find/{id}")
    public String blogFindById(Long id, Model model) {
        BlogEntity blogEntity = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog post with "+ id + " is not exits."));
        model.addAttribute("blog_find", blogEntity);
        return "blog_detail_page";
    }

    // DELETE
    // http://localhost:3333/blog/delete
    // http://localhost:3333/blog/delete/1
    @Override
    @GetMapping({"/blog/delete", "/blog/delete/{id}"})
    public String blogDeleteById(Long id, Model model) {
        BlogEntity blogEntity = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog post with " + id + " is not exist."));
        if (blogEntity != null) {
            blogRepository.deleteById(id);
            model.addAttribute("key_delete", blogEntity + " deleted");
        } else
            model.addAttribute("key_delete", "Data with id "+ id + " is not exist");
        return "redirect:/blog/list";
    }

    // UPDATE
    // http://localhost:3333/blog/update
    @Override
    @GetMapping("/blog/update/{id}")
    public String updateGetBlog(Long id, Model model) {
        BlogEntity blogEntityFind = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (blogEntityFind != null) {
            model.addAttribute("key_update", blogEntityFind);
        } else
            model.addAttribute("key_update", "Data with id "+ id + " is not exist");
        return "blog_update";
    }

    // UPDATE
    // http://localhost:3333/blog/update
    @Override
    @PostMapping("/blog/update/{id}")
    public String updatePostBlog(Long id, BlogDto blogDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR: " + bindingResult);
            return "blog_update";
        }
        BlogEntity blogEntity = modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
        try {
            if (blogEntity != null) {
                blogRepository.save(blogEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/blog/list";
    }
}
