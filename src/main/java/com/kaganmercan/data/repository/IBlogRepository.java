package com.kaganmercan.data.repository;

import com.kaganmercan.data.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kaganmercan
 */
// Direct database access and interaction
@Repository
public interface IBlogRepository extends JpaRepository<BlogEntity, Long> {
}

