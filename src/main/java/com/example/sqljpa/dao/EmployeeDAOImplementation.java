package com.example.sqljpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.sqljpa.model.Employee;

@Repository
public class EmployeeDAOImplementation {

	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Employee> getAllEmployeeDAONativeQuery(){
		return manager.createNamedQuery("getAllRecordsByNative", Employee.class).getResultList();
	}
	
}
