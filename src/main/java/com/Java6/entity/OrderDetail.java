package com.Java6.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "orders_details")
public class OrderDetail  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Double price;
	Integer quantity;
	@ManyToOne
	@JoinColumn(name = "product_id")
	Product product;
	@ManyToOne
	@JoinColumn(name = "order_id")
	Order order;
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order=" + order + ", price=" + price + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}
}
