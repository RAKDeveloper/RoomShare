package com.example.sqljpa.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.example.sqljpa.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	
	String saveMultipleEmployee(List<Employee> employee);
	
	String saveEmployeeWithQuery(String name,Long age,String location,String email,String department);
	
	List<Employee> getEmployeeWithNativeQuery();
	
	List<Employee> getEmployee();
	
	List<Employee> getEmployeePagination(int pageNumber, int pageCount);
	
	List<Employee> getEmployeePriceBetween(BigDecimal start,BigDecimal end);
	
	List<Employee> getEmployeeDateBetween(Date start,Date end);
	
	List<Employee> getEmployeeNameByIn(List<String> listName);
	
	List<Employee> getEmployeePaginationOrder(int pageNumber, int pageCount);
	
	Employee getEmployeeById(Long id);
	
	List<Employee> getEmployeeByNameAndSalary(String name,BigDecimal salary);
	
	List<Employee> getEmployeeBySalaryGreateThan(BigDecimal salary);
	
	List<Employee> getEmployeeBySalaryLessThan(BigDecimal salary);
	
	List<Employee> getEmployeeByNameAndLocation(String name,String location);
	
	List<Employee> getEmployeeByNameAndLocationWithQuery(String name,String location);
	
	List<Employee> getEmployeeByNameContaining(String keyword);
	
	List<Employee> getEmployeeByNameLike(String keyword);
	
	List<Employee> getEmployeeByNameOrder(String keyword);
		
	Employee updateEmployee(Employee employee);
	
	Employee updateEmployeeWithUrlID(Employee employee);
	
	Integer updateEmployeeWithQuery(String name,String email,Long id);
	
	void deleteEmployee(Long id);
	
	Integer deleteEmployeeByNameWithQuery(String name);
	
	String deleteEmployeeWithMsg(Long id);
	
	
}
