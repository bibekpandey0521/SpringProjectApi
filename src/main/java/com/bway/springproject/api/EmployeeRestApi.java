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
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeRestApi {
	
	@Autowired
	private ProductRepository prodRepo;

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
	
	@GetMapping("/api/emp/j2o")
	public String jsonToObject() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Employee emp = restTemplate.getForObject("http://localhost:8080/api/emp/5", Employee.class);	
		
		return "FirstName = " +emp.getFname();
//		return "Address : "+emp.getAddress();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String jsonArraytoObjectArray() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Employee []  emps = restTemplate.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
		return "fname = "+emps[1].getAddress().getCity();
//		return "fname = "+emps[1].getAddress().getCity();
		
	}
	
	@GetMapping("/api/emp/load")
	public String loadProducts() {
		
		RestTemplate restTemplate = new RestTemplate();
//		String url = "https://fakestoreapi.com/products";
		Product[] plist =restTemplate.getForObject("https://fakestoreapi.com/products",Product[].class);
		
		prodRepo.saveAll(List.of(plist));
//		for (Product p : plist) {
//            addProduct(p);
//        }
		return "success";
	}
}
