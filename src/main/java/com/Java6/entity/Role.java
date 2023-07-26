package com.Java6.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable{
	@Id
	private Long id;
	private String name;

	@JsonIgnore

	@OneToMany(mappedBy = "role")
	List<Authority> authorities;
	@Override
	public String toString() {
		return "Role [authorities=" + ", id=" + id + ", name=" + name + "]";
	}
}
