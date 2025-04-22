package com.bway.springproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bway.springproject.model.Department;
import com.bway.springproject.service.DepartmentService;
import com.bway.springproject.utilis.DepartmentExcelView;
import com.bway.springproject.utilis.DepartmentPdfView;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/add")
	public String getDepartment() {
		return "DepartmentForm";
	}
	@PostMapping("/add")
	public String postDepartment(@ModelAttribute Department dept,Model model) {
		departmentService.addDepartment(dept);
		return "DepartmentForm";
	}
	@GetMapping("/list")
	public String getDepartment(Model model) {
		model.addAttribute("dList",departmentService.getAllDepartments());
		return "DepartmentListForm";
	}
	
	@GetMapping("/delete")
	public String deleteDept(@RequestParam("id") int id) {
		departmentService.deleteDepartment(id);
		return "redirect:list";
	}
	
	@GetMapping("/edit")
	public String editDept(@RequestParam("id") int id, Model model){
		model.addAttribute("dModel",departmentService.getDepartmentById(id));
		return "DepartmentEditForm";
	}
	
	@PostMapping("/update")
	public String updateDept(@ModelAttribute Department dept) {
		departmentService.updateDepartment(dept);
		return "redirect:list";
	}
	
	@GetMapping("/excel")
	public ModelAndView excel() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dList",departmentService.getAllDepartments());
		mv.setView(new DepartmentExcelView());
		
		return mv;
	}
	
	
	@GetMapping("/pdf")
	public ModelAndView pdf() {
		
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dList",departmentService.getAllDepartments());
		mv.setView(new DepartmentPdfView());
		
		return mv;
	}
}
