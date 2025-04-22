package com.bway.springproject.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Employee;
import com.bway.springproject.repository.EmployeeRepository;
import com.bway.springproject.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public void addEmp(Employee emp) {
		empRepo.save(emp);

	}

	@Override
	public void deleteEmp(long id) {
		empRepo.deleteById(id);

	}

	@Override
	public void updateEmp(Employee emp) {
		empRepo.save(emp);
	}

	@Override
	public Employee getEmpById(long id) {
		return empRepo.findById(id).get();
	}

	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	@Override
	public Optional<Employee> findById(long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id);
	}

}
