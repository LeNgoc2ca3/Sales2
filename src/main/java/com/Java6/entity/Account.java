package com.Java6.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@Entity 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account  implements Serializable{
	@Id
	Long id;
	String username;
	String password;
	String fullname;
	String email;
	String photo;

	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "id")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
	List<Authority> authorities;

}

