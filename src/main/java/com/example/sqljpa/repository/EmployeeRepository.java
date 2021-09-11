package com.example.sqljpa.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.sqljpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> getEmployeeByNameAndSalary(String name,BigDecimal salary);
	
	public List<Employee> findBySalaryGreaterThan(BigDecimal salary);
	
	public List<Employee> findBySalaryLessThan(BigDecimal salary);
	
	public List<Employee> findBySalaryBetween(BigDecimal start,BigDecimal end);
	
	public List<Employee> findByCreatedAtBetween(Date start, Date end);
	
	public List<Employee> findByNameIn(List<String> listName);
	
	public List<Employee> findEmployeeByNameAndLocation(String name,String location);
	
	//JPQL SELECT Query
	@Query("FROM Employee WHERE name = :name OR location = :location")
	public List<Employee> findEmployeeByNameAndLocationWithQuery(@Param("name") String name,@Param("location") String location);
	
	//Native SQL Query
	@Query(value = "select * from tbl_employee", nativeQuery = true)
	public List<Employee> getEmployeeWithNativeQuery();

	//JPA Query from Model Class : method name should be same
	public List<Employee> getEmployeeWithJPAModelQuery();
		
	public List<Employee> findEmployeeByNameContaining(String keyword);
	
	public List<Employee> findEmployeeByNameLike(String keyword);	
	
	//JPQL DELETE Query
	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name = :name")
	Integer findByDeleteEmployeeByNameWithQuery(@Param("name") String name);
	
	//JPQL UPDATE Query
	@Transactional
	@Modifying
	@Query("UPDATE Employee SET name = :name, email = :email WHERE id = :id")
	Integer findByUpdateEmployeeByWithQuery(@Param("name") String name,@Param("email") String email,@Param("id") Long id);
	
	//JPQL CREATE Query
	@Transactional
	@Modifying
	@Query(value = "insert into tbl_employee(name,age,location,email,department) select :name, :age, :location, :email, :department",nativeQuery = true)
	Integer findByCreateEmployeeWithQuery(@Param("name") String name,
			@Param("age") Long age,
			@Param("location") String location,
			@Param("email") String email,
			@Param("department") String department);
	
	
}