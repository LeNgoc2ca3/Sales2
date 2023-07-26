package com.Java6.service;

import java.util.List;

import com.Java6.dto.OrderDto;
import com.Java6.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.http.HttpServletRequest;


public interface OrderService {

    Order create(JsonNode orderData, HttpServletRequest request);
    void print();
    Order findById(long id);
    List<Order> findByUsername(String username);
    
}
