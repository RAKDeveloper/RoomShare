package com.example.sqljpa.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.sqljpa.model.Employee;
import com.example.sqljpa.repository.EmployeePagingSortingRepository;
import com.example.sqljpa.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository eRepository ;
	
	@Autowired
	private EmployeePagingSortingRepository ePS_Repository ;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return eRepository.save(employee);
	}

	@Override
	public String saveMultipleEmployee(List<Employee> employee) {
		eRepository.saveAll(employee);
		return "Employee data save sucessfully";
	}
	
	@Override
	public String saveEmployeeWithQuery(String name, Long age, String location, String email, String department) {
		eRepository.findByCreateEmployeeWithQuery(name, age, location, email, department);
		return "Employee data save sucessfully";
	}
	
	@Override
	public List<Employee> getEmployee() {
		return eRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeePagination(int pageNumber, int pageCount) {
		Pageable pages = PageRequest.of(pageNumber, pageCount);
		return ePS_Repository.findAll(pages).getContent();
	}
	
	@Override
	public List<Employee> getEmployeePaginationOrder(int pageNumber, int pageCount) {
		Pageable pages = PageRequest.of(pageNumber, pageCount,Direction.DESC,"id","name");
		return ePS_Repository.findAll(pages).getContent();
	}
	
	@Override
	public Employee getEmployeeById(Long id) {
	 Optional<Employee> employee = eRepository.findById(id);
	 if(employee.isPresent()) {
		 return employee.get();
	 }
	 throw new RuntimeException("Employee not found for id "+  id);
	 
	}
	
	@Override
	public List<Employee> getEmployeeByNameAndSalary(String name,BigDecimal salary) {
		return (List<Employee>) eRepository.getEmployeeByNameAndSalary(name,salary);
	}
	
	@Override
	public List<Employee> getEmployeeBySalaryGreateThan(BigDecimal salary) {
		return (List<Employee>) eRepository.findBySalaryGreaterThan(salary);
	}

	@Override
	public List<Employee> getEmployeeBySalaryLessThan(BigDecimal salary) {
		return (List<Employee>) eRepository.findBySalaryLessThan(salary);
	}
	
	@Override
	public List<Employee> getEmployeePriceBetween(BigDecimal start, BigDecimal end) {
		return (List<Employee>) eRepository.findBySalaryBetween(start,end);
	}

	@Override
	public List<Employee> getEmployeeDateBetween(Date start, Date end) {
		return (List<Employee>) eRepository.findByCreatedAtBetween(start,end);
	}

	@Override
	public List<Employee> getEmployeeNameByIn(List<String> listName) {
		return (List<Employee>) eRepository.findByNameIn(listName);
	}
	
	@Override
	public List<Employee> getEmployeeByNameAndLocation(String name, String location) {
		return (List<Employee>) eRepository.findEmployeeByNameAndLocation(name,location);
	}
	
	@Override
	public List<Employee> getEmployeeByNameAndLocationWithQuery(String name, String location) {
		return (List<Employee>) eRepository.findEmployeeByNameAndLocationWithQuery(name,location);	
	}
	
	@Override
	public List<Employee> getEmployeeWithNativeQuery() {
		return (List<Employee>) eRepository.getEmployeeWithNativeQuery();
	}

	@Override
	public List<Employee> getEmployeeByNameContaining(String keyword) {
		return (List<Employee>) eRepository.findEmployeeByNameContaining(keyword);
	}
	
	@Override
	public List<Employee> getEmployeeByNameLike(String keyword) {
		return (List<Employee>) eRepository.findEmployeeByNameLike(keyword);
	}
	
	@Override
	public List<Employee> getEmployeeByNameOrder(String keyword) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return (List<Employee>) ePS_Repository.findEmployeeByNameContaining(keyword,sort);
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		return eRepository.save(employee);
	}
	
	@Override
	public Employee updateEmployeeWithUrlID(Employee employee) {
		return eRepository.save(employee);
	}
	
	@Override
	public Integer updateEmployeeWithQuery(String name, String email, Long id) {
		return eRepository.findByUpdateEmployeeByWithQuery(name,email,id);
	}

	@Override
	public void deleteEmployee(Long id) {
		eRepository.deleteById(id);	
	}

	@Override
	public String deleteEmployeeWithMsg(Long id) {
		Optional<Employee> emloyee = eRepository.findById(id);
		if(emloyee.isPresent()) {
			eRepository.deleteById(id);
			return "Employee deleted successfully";
		}
		throw new RuntimeException("Employee not found for thid id " + id);
	}
	
	@Override
	public Integer deleteEmployeeByNameWithQuery(String name) {
		return eRepository.findByDeleteEmployeeByNameWithQuery(name);
	}

}
