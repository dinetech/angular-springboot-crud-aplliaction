package com.in.alien.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.alien.exception.ResourceNotFoundException;
import com.in.alien.model.Employee;
import com.in.alien.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Get All employees
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){		
		return employeeRepository.findAll();
	}
	
	//Get Employee by Id
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getbyid(@PathVariable Long id){
		Employee employee=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exits with id:"+id));
		return ResponseEntity.ok(employee);
	}
	
	// Create New employee
	
	@PostMapping("/employees")
	public Employee addNewEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
	//Update employee
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,  @RequestBody Employee employeeDetails){
		Employee employee=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exits with id:"+id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee=employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmmployee(@PathVariable long id){		
		Employee employee=employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not exits with id:"+id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response= new HashMap<>();
		response.put("Employee Successfully Deleted : " +id, Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
