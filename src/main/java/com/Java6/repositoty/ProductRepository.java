package com.Java6.repositoty;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Java6.entity.Product;
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	@Query(value = "SELECT COUNT(*) FROM products",nativeQuery = true)
	Integer getTotalProductCount();

	@Query("SELECT p FROM Product p WHERE p.name like ?1")
	List<Product> findAll(String name, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.available = 1")
	List<Product> findAllAvai();
}
