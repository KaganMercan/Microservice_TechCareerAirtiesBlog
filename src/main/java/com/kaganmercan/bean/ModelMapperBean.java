package com.kaganmercan.bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kaganmercan
 */
@Configuration
public class ModelMapperBean {
    // Model mapper provides conversions between DTO and Entity relation.
    @Bean
    public ModelMapper modelMapperMethod(){
        return new ModelMapper();
    }
}
