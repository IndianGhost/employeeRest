package com.rest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.model.Employee;
import com.rest.repository.EmployeeRepository;

@Service
public class EmployeeDao {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/*
	 * Save an employee
	 */
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	/*
	 * Search all employees
	 */
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	/*
	 * Get an employee by id
	 */
	public Employee findOne(Long id) {
		return employeeRepository.findOne(id);
	}
	
	/*
	 * Delete an employee
	 */
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
}
