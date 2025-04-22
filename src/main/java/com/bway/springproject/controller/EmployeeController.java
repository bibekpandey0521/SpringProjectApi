package com.bway.springproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.DepartmentService;
import com.bway.springproject.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/add")
	public String getEmployeeForm(Model model) {
		model.addAttribute("dList",deptService.getAllDepartments());
		return "EmployeeForm";
	}
	
	
	@PostMapping("/add")
	public String postEmployee(@ModelAttribute Employee emp,Model model) {
		if(emp.getFname().isBlank()) {
			model.addAttribute("message","fname is required");
			return "redirect:add";
		}
		empService.addEmp(emp);
		return "redirect:add";
	}
	
	@GetMapping("/list")
	public String getEmployee(Model model) {
		model.addAttribute("eList", empService.getAllEmployee());
		return "EmployeeListForm";
	}
	@GetMapping("/delete")
	public String deleteDept(@RequestParam("id") long id) {
		empService.deleteEmp(id);
		return "redirect:list";
	}
	@GetMapping("/edit")
	public String editEmp(@RequestParam("id") long id, Model model) {
		model.addAttribute("eModel",empService.getEmpById(id));
		return "EmployeeEditForm";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee emp) {
	    // Fetch existing employee from the database
	    Employee existingEmp = empService.getEmpById(emp.getId());
	    
	    // Update only the fields present in the form
	    existingEmp.setFname(emp.getFname());
	    existingEmp.setCompany(emp.getCompany());
	    existingEmp.setPhone(emp.getPhone());
	    
	    // Save the updated employee
	    empService.updateEmp(existingEmp);
	    
	    return "redirect:list";
	}
	
	
	
//	@PostMapping("/update")
//	public String updateEmp(@ModelAttribute Employee emp) {
//		empService.updateEmp(emp);
//		return "redirect:list";
//	}
	
	
	
	@GetMapping("/{id}")
	public String employee(@PathVariable long id, Model model) {
        Optional<Employee> employee = empService.findById(id);

        employee.ifPresent(value -> model.addAttribute("employee", value));

        return "EmpolyeeShow";
		
	}
}
