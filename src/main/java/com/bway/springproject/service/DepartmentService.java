package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Department;

public interface DepartmentService {
	void addDepartment(Department department);
	void updateDepartment(Department department);
	void deleteDepartment(int id);
	Department getDepartmentById(int id);
	List<Department> getAllDepartments();
}	
