package com.example.sqljpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.sqljpa.model.Employee;

@Repository
public interface EmployeePagingSortingRepository extends PagingAndSortingRepository<Employee, Long> {

	public List<Employee> findEmployeeByNameContaining(String keyword, Sort sort);

}
