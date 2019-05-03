package com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
