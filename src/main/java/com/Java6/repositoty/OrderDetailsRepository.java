package com.Java6.repositoty;

import com.Java6.entity.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, String>{
    
}
