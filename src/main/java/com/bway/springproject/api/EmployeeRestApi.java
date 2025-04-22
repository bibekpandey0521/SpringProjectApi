package com.bway.springproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.EmployeeService;
import java.util.List;

@RestController
public class EmployeeRestApi {

	@Autowired
	private EmployeeService empService;

	@GetMapping("/api/emp/list")
	public List<Employee> getAll() {

		return empService.getAllEmployee();
	}

	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable("id") long id) {

		return empService.getEmpById(id);
	}

	@PostMapping("/api/emp/add")
	public String add(@RequestBody Employee emp) {

		empService.addEmp(emp);
		return "added success";
	}
	
	@DeleteMapping("/api/emp/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		
		empService.deleteEmp(id);
		return "delete success";
	}
	
	@PutMapping("/api/emp/update")
	public ResponseEntity<?> update(@RequestBody Employee emp){
		
		empService.addEmp(emp);
		return new ResponseEntity<>("Update Success",HttpStatus.OK);
	}
}
