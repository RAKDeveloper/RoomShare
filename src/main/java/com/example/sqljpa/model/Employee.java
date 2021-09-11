package com.example.sqljpa.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tbl_employee")
@NamedNativeQuery(name = "getAllRecordsByNative", query = "select * from tbl_employee", resultClass = Employee.class )//for DAO native query
@NamedQuery(name = "Employee.getEmployeeWithJPAModelQuery", query = "FROM Employee")//for jpa query
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "name should not be null")
//	@NotEmpty(message = "name should not be null")
//	@NotNull(message = "name should not be null")
	@Column(name = "name")
//	@JsonProperty("full_name")
	private String name;
	
	@Column(name = "age")
//	@JsonIgnore
	private Long age = 0L;
	
	@NotBlank(message = "location should not be null")
//	@NotEmpty(message = "location should not be null")
//	@NotNull(message = "location should not be null")
	@Column(name = "location")
	private String location;
	
	@Email(message = "enter valid email address")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "department should not be null")
//	@NotEmpty(message = "department should not be null")
//	@NotNull(message = "department should not be null")
	@Column(name = "department")
	private String department;
	
	@Column(name = "salary")
	private BigDecimal salary;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Date getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(Date created_at) {
		this.createdAt = created_at;
	}

	public Date getUpdated_at() {
		return updatedAt;
	}

	public void setUpdated_at(Date updated_at) {
		this.updatedAt = updated_at;
	}
	
	
}
