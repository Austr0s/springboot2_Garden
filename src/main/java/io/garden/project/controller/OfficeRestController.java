package io.garden.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.garden.project.model.entity.Office;
import io.garden.project.service.OfficeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1/office")
public class OfficeRestController {
	
	@Autowired
	private OfficeService service;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Find Office by Id", notes = "Provide an id  to look up specific Office from Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Api"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})	
	public ResponseEntity<Optional<Office>> findOfficeById(@PathVariable(value = "id") Long id) {
		Optional<Office> officeOptional = service.findOneById(id);		
		return (officeOptional.isPresent()) ? ResponseEntity.ok(officeOptional) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	@ApiOperation(value = "Find all Clients", notes = "Returns all Offices from Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Offices"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public ResponseEntity<Iterable<Office>> findAllOffices() {
		Iterable<Office> officesIterable = service.findAll();
		return ResponseEntity.ok(officesIterable);
	}
	
	@PostMapping
	@ApiOperation(value = "Create a new Office", notes = "Returns new Office created and saved into Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Office"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public ResponseEntity<Office> create(@RequestBody(required = true) Office office) {
		
		Office officeCreated = service.create(office);
		
		return ResponseEntity.ok(officeCreated);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update an existing Office", notes = "Returns Office updated and saved into Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Updated Office"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public ResponseEntity<Office> update(@RequestBody(required = true) Office office, @PathVariable Long id){
		Optional<Office> officeOptional = service.findOneById(id);

		if (!officeOptional.isPresent())
			return ResponseEntity.notFound().build();

		office.setId(id);
		
		Office  officeUpdated = service.update(office);

		return ResponseEntity.ok(officeUpdated);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete an existing Office", notes = "Returns nothing after operaton", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public void delete(@PathVariable Long id) throws NotFoundException{
		Optional<Office> officeOptional = service.findOneById(id);

		if (!officeOptional.isPresent())
			throw new NotFoundException("Entity not found");

		service.delete(id);
	}

}
