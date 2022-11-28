package com.kaganmercan.ui.mvc;

import com.kaganmercan.business.dto.BlogDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * @author kaganmercan
 */
public interface IBlogController {
    public String createSpeedData(Model model);
    public String deleteSpeedData(Model model);
    public String createGetBlog(Model model);
    public String createPostBlog(BlogDto blogDto, BindingResult bindingResult, Model model);
    public String blogList(Model model);
    public String blogFindById(Long id, Model model);
    public String blogDeleteById( Long id, Model model);
    public String updateGetBlog(Long id, Model model);
    public String updatePostBlog(Long id, BlogDto blogDto, BindingResult bindingResult, Model model);
}
