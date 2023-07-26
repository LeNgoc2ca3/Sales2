package com.Java6.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Java6.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
