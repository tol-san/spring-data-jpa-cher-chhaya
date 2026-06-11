package com.ecommerce.repository;

import com.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    List<Category> findByParentCategoryIdAndIsDeletedFalse(Integer id);
}
