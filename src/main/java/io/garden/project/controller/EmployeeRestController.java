package io.garden.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.garden.project.model.entity.Employee;
import io.garden.project.model.util.ResourceNotFoundException;
import io.garden.project.service.EmployeeService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee", tags = { "Employee" })
public class EmployeeRestController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<?> findOneEmployee(@PathVariable Long employeeId) {
		Employee employeeResonse = service.findOneEmployee(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + employeeId + " was not found"));
		return ResponseEntity.ok(employeeResonse);
	}
	
	@GetMapping("/offices/{officeId}/employees/{employeeId}")
	public ResponseEntity<?> findByIdAndOfficeId(@PathVariable Long officeId, @PathVariable Long employeeId){
		Employee employeeResonse = service.findByIdAndOfficeId(employeeId, officeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + employeeId + " was not found"));
		
		return ResponseEntity.ok(employeeResonse);
	}
	
	@GetMapping("/employees/{bossId}/{employeeId}")
	public ResponseEntity<?> findByIdAndBossEmployeeId(@PathVariable Long bossId, @PathVariable Long employeeId){
		Employee employeeResonse = service.findByIdAndBossEmployeeId(employeeId, bossId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + employeeId + " was not found"));
		
		return ResponseEntity.ok(employeeResonse);
	}
	
	@GetMapping("/offices/{officeId}/employees")
	public Page<Employee> findByOfficeId(@PathVariable Long officeId, Pageable pageable){
		return service.findByOfficeId(officeId, pageable);
	}
	
	@GetMapping("/employees/bosses/{bossId}")
	public Page<Employee> findByBossEmployeeId(@PathVariable Long bossId, Pageable pageable){
		return service.findByBossEmployeeId(bossId, pageable);
	}
	
	@GetMapping("/employees")
	public Page<Employee> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	

}
