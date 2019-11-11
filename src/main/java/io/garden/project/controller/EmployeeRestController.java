package io.garden.project.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.garden.project.model.entity.Client;
import io.garden.project.model.entity.Employee;
import io.garden.project.model.entity.Office;
import io.garden.project.model.util.ResourceNotFoundException;
import io.garden.project.service.ClientService;
import io.garden.project.service.EmployeeService;
import io.garden.project.service.OfficeService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee", tags = { "Employee" })
public class EmployeeRestController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private OfficeService officeService;


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
	
	@GetMapping("/bosses/{bossId}/employees")
	public Page<Employee> findByBossEmployeeId(@PathVariable Long bossId, Pageable pageable){
		return service.findByBossEmployeeId(bossId, pageable);
	}
	
	@GetMapping("/employees")
	public Page<Employee> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@PostMapping("/employees")
	public Employee create(@Valid @RequestBody(required = true) Employee employee) {
		
		validateOffice(employee.getOffice().getId());
		
		return service.create(employee);
	}
	
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<?> update(@Valid @RequestBody(required = true) Employee employee, @PathVariable Long employeeId) {
		if(!employee.getId().equals(employeeId))
			new ResourceNotFoundException("Employee Id: " + employeeId + " isn't the same of Employee to update: " + employee.getId());
		
		Employee responseEmployee = service.findOneEmployee(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + employeeId + " was not found"));

		if (validateOffice(responseEmployee.getOffice().getId()))
			return null;
		
		Office responseOffice = officeService.findOneById(responseEmployee.getOffice().getId()).orElseThrow(() -> new ResourceNotFoundException("Office Id: " + responseEmployee.getOffice().getId()
					+ " Doesn't exist. You can't create an Employee without Office."));

		
		employee.setId(responseEmployee.getId());

		return ResponseEntity.ok(service.update(employee));
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<?> delete(@PathVariable Long employeeId) throws NotFoundException {
		Employee responseEmployee = service.findOneEmployee(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Id: " + employeeId + " was not found"));
		
		Optional<Client> hasGotClients = clientService.findTopByEmployeeId(responseEmployee.getId());
		
		if (hasGotClients.isPresent())
			throw new ResourceNotFoundException("Employee Id: " + employeeId
					+ " has got Clients. If you want to delete this Employee...  you have to delete Clients' relationship first");

		service.delete(responseEmployee.getId());

		return ResponseEntity.noContent().build();
	}
	
	
	private boolean validateOffice(Long officeId) {
		Optional<Office> responseOffice = officeService.findOneById(officeId);
		return !responseOffice.isPresent();
//		if(!responseOffice.isPresent())
//			throw new ResourceNotFoundException("Office Id: " + officeId
//					+ " Doesn't exist. You can't create an Employee without Office.");
	}

}
