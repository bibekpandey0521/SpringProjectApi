package com.bway.springproject.service;

import java.util.List;
import java.util.Optional;

import com.bway.springproject.model.Employee;

public interface EmployeeService {

	void addEmp(Employee emp);

	void deleteEmp(long id);

	void updateEmp(Employee emp);

	Employee getEmpById(long id);

	List<Employee> getAllEmployee();

	 Optional<Employee> findById(long id);
}
