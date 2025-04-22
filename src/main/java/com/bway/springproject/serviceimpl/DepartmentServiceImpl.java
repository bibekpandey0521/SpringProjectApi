package com.bway.springproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Department;
import com.bway.springproject.repository.DepartmentRepository;
import com.bway.springproject.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public void addDepartment(Department department) {
		departmentRepository.save(department);
		
	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepository.save(department);
		
	}

	@Override
	public void deleteDepartment(int id) {
		departmentRepository.deleteById(id);
		
	}

	@Override
	public Department getDepartmentById(int id) {
		
		return departmentRepository.findById(id).get();
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}
	
}
