package com.Java6.service.impl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import com.Java6.repositoty.OrderDetailsRepository;
import com.Java6.repositoty.OrderRepository;
import com.Java6.service.AccountService;
import com.Java6.util.ObjectMapperUtils;
import com.Java6.entity.Order;
import com.Java6.entity.OrderDetail;
import com.Java6.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AccountService accountService;

    @Override
    public Order create(JsonNode orderData, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Order order = objectMapper.convertValue(orderData, Order.class);
        order.setAccount(accountService.findById(principal.getName()));
        orderRepository.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> orderDetails = objectMapper.convertValue(orderData.get("orderDetails"), type).stream()
                .peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailsRepository.saveAll(orderDetails);
        return order;
    }

    @Override
    public void print() {
        System.out.println("2");
    }

    @Override
    public Order findById(long id) {
        // Order order = orderRepository.findById(id).get();
        // OrderDto result = objectMapperUtils.convertEntityAndDto(order, OrderDto.class);
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
//        System.out.println(orderRepository.findByUsername(username));
        return orderRepository.findByUsername(username);
    }

}
