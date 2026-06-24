package com.ecommerce.feature.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    List<Category> findByParentCategoryIdAndIsDeletedFalse(Integer id);
}
