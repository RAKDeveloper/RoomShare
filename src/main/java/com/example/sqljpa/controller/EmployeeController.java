package com.example.sqljpa.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sqljpa.dao.EmployeeDAOImplementation;
import com.example.sqljpa.model.Employee;
import com.example.sqljpa.repository.EmployeeRepository;
import com.example.sqljpa.services.EmployeeService;

@RestController
//@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@Autowired
	private EmployeeDAOImplementation eDao;

	//App Details Method
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.version}")
	private String appVersion;

	@GetMapping("/version")
	public ResponseEntity<String> getAppDetails() {	
	return new ResponseEntity<String>(appName + " - " + appVersion,HttpStatus.OK);
	}

	//Common Method
	@PostMapping("/employees_save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {	
	return new ResponseEntity<Employee>(eService.saveEmployee(employee),HttpStatus.CREATED);
	}
	
	@PostMapping("/employees_save_all")
	public ResponseEntity<String> saveMultipleEmployee(@RequestBody List<Employee> employee) {	
	return new ResponseEntity<String>(eService.saveMultipleEmployee(employee),HttpStatus.CREATED);
	}
	
	@PostMapping("/employees_save_with_query")
	public ResponseEntity<String> saveEmployeeWithQuery(@RequestParam("name")String name,@RequestParam("age")Long age,@RequestParam("location")String location,@RequestParam("email")String email,@RequestParam("department")String department) {	
	return new ResponseEntity<String>(eService.saveEmployeeWithQuery(name, age, location, email, department),HttpStatus.CREATED);
	}
	
	@GetMapping("/employees_list")
	public ResponseEntity<List<Employee>> getEmployee() {	
	return new ResponseEntity<List<Employee>>(eService.getEmployee(),HttpStatus.OK);
	}
	
	@GetMapping("/employees_list_pagination")
	public ResponseEntity<List<Employee>> getEmployeePagination(@RequestParam("pageNumber") Integer pageNumber,@RequestParam("pageCount") Integer pageCount) {	
	return new ResponseEntity<List<Employee>>(eService.getEmployeePagination(pageNumber,pageCount),HttpStatus.OK);
	}
	
	@GetMapping("/employees_list_pagination_order")
	public ResponseEntity<List<Employee>> getEmployeePaginationOrder(@RequestParam("pageNumber") Integer pageNumber,@RequestParam("pageCount") Integer pageCount) {	
	return new ResponseEntity<List<Employee>>(eService.getEmployeePaginationOrder(pageNumber,pageCount),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_id")
	public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Long id) {	
	return new ResponseEntity<Employee>(eService.getEmployeeById(id),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_name_salary")
	public ResponseEntity<List<Employee>> geEmployeeByName(@RequestParam("name") String name,@RequestParam("salary") BigDecimal salary) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndSalary(name,salary),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_salary_greater_than")
	public ResponseEntity<List<Employee>> geEmployeeBySalaryGreateThan(@RequestParam("salary") BigDecimal salary) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeBySalaryGreateThan(salary),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_salary_less_than")
	public ResponseEntity<List<Employee>> geEmployeeBySalaryLessThan(@RequestParam("salary") BigDecimal salary) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeBySalaryLessThan(salary),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_salary_between")
	public ResponseEntity<List<Employee>> getEmployeePriceBetween(@RequestParam("start") BigDecimal start,@RequestParam("end") BigDecimal end) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeePriceBetween(start,end),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_date_between")
	public ResponseEntity<List<Employee>> getEmployeeDateBetween(@RequestParam("start") Date start,@RequestParam("end") Date end) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeDateBetween(start,end),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_name_in")
	public ResponseEntity<List<Employee>> getEmployeeNameByIn() {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeNameByIn(Arrays.asList("Ajay","Navneet")),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_name_location")
	public ResponseEntity<List<Employee>> geEmployeeByNameAndLocation(@RequestParam("name") String name,@RequestParam("location") String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocation(name,location),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_name_location_with_query")
	public ResponseEntity<List<Employee>> getEmployeeByNameAndLocationWithQuery(@RequestParam("name") String name,@RequestParam("location") String location) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameAndLocationWithQuery(name,location),HttpStatus.OK);
	}
	
	@GetMapping("/employees_list_with_native_query")
	public ResponseEntity<List<Employee>> getEmployeeWithNativeQuery() {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeWithNativeQuery(),HttpStatus.OK);
	}
	
	@GetMapping("/employees_list_with_dao_native_query")
	public ResponseEntity<List<Employee>> getEmployeeWithDAONativeQuery() {
		return new ResponseEntity<List<Employee>>(eDao.getAllEmployeeDAONativeQuery(),HttpStatus.OK);
	}
	
	@GetMapping("/employees_list_with_model_jpa_query")
	public ResponseEntity<List<Employee>> getEmployeeWithJPAModelQuery() {
		return new ResponseEntity<List<Employee>>(eRepo.getEmployeeWithJPAModelQuery(),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_conatin")
	public ResponseEntity<List<Employee>> getEmployeeByNameContaining(@RequestParam("name") String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameContaining(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_like")
	public ResponseEntity<List<Employee>> getEmployeeByNameLike(@RequestParam("name") String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameLike("%"+name+"%"),HttpStatus.OK);
	}
	
	@GetMapping("/employees_by_order")
	public ResponseEntity<List<Employee>> getEmployeeByNameOrder(@RequestParam("name") String name) {
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameOrder(name),HttpStatus.OK);
	}
	
	@PutMapping ("/employees_update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(eService.updateEmployee(employee),HttpStatus.OK);
	}
	
	@PutMapping ("/employees_update_id")
	public ResponseEntity<Employee> updateEmployeeWithUrlID(@RequestParam("id") Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployeeWithUrlID(employee),HttpStatus.OK);
	}
	
	@PutMapping ("/employees_update_with_query")
	public ResponseEntity<String> updateEmployeeWithQuery(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("id") Long id) {
		return new ResponseEntity<String>(eService.updateEmployeeWithQuery(name,email,id) + " Recorde upated for id "+ id,HttpStatus.OK);
	}
	
	@DeleteMapping("/employees_delete")
	public void deleteEmployee(@RequestParam("id") Long id) {	
		eService.deleteEmployee(id);
	}
	
	@DeleteMapping("/employees_delete_msg")
	public ResponseEntity<String> deleteEmployeeWithMsg(@RequestParam("id") Long id) {	
		return new ResponseEntity<String>(eService.deleteEmployeeWithMsg(id),HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/employees_delete_by_name_with_query")
	public ResponseEntity<String> deleteEmployeeByNameWithQuery(@RequestParam("name") String name) {	
		return new ResponseEntity<String>(eService.deleteEmployeeByNameWithQuery(name) + " No of record deleted.",HttpStatus.NO_CONTENT);
	}
	
	
}
