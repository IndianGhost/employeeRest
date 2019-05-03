package com.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dao.EmployeeDao;
import com.rest.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;
	
	/* To save an employee */
	@PostMapping("/")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeDao.save(employee);
	}
	
	/* To get all employees */
	@GetMapping("/")
	public List<Employee> getAllEmployees(){
		return employeeDao.findAll();
	}
	
	/* Get employee by id */
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id){
		Employee employee = employeeDao.findOne(id); 
		if(employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(employee);
	}
	
	/* Update an employee by id */
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployeeById(
			@PathVariable(value = "id") Long id, 
			@Valid @RequestBody Employee employeeDetails
	) {
		Employee employee = employeeDao.findOne(id);
		if(employee == null)
			return ResponseEntity.notFound().build();
		
		employee.setName(employeeDetails.getName());
		employee.setDesignation(employeeDetails.getDesignation());
		employee.setExpertise(employeeDetails.getExpertise());
		employee.setCreatedAt(employeeDetails.getCreatedAt());
		
		Employee updateEmployee = employeeDao.save(employee);
		
		return ResponseEntity.ok().body(updateEmployee);
	}
	
	/* Delete an employee By Id */
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable(value = "id") Long id) {
		Employee employee = employeeDao.findOne(id);
		
		if(employee == null)
			return ResponseEntity.notFound().build();
		
		employeeDao.delete(employee);
		
		return ResponseEntity.ok().build();
	}
}
