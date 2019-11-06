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
import io.garden.project.model.entity.Office;
import io.garden.project.model.util.ResourceNotFoundException;
import io.garden.project.service.OfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1/offices")
@Api(value = "Office", tags = {"Office"})
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
	public Optional<Office> findOfficeById(@PathVariable(value = "id") Long id) {
		return service.findOneById(id);
	}
	
	@GetMapping("/offices")
	@ApiOperation(value = "Find all Clients", notes = "Returns all Offices from Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Offices"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public Page<Office> findAllOffices(Pageable pageable) {		
		return service.findAll(pageable);
	}
	
	@PostMapping
	@ApiOperation(value = "Create a new Office", notes = "Returns new Office created and saved into Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Saved Office"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public Office create(@Valid @RequestBody(required = true) Office office) { 
		return service.create(office);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update an existing Office", notes = "Returns Office updated and saved into Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Updated Office"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public Office update(@Valid @RequestBody(required = true) Office office, @PathVariable Long id){
		Office responseOffice = service.findOneById(id).orElseThrow(() -> new ResourceNotFoundException("OfficeId: " + id + " was not found"));
		
		office.setId(responseOffice.getId());
		
		return service.update(office);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete an existing Office", notes = "Returns nothing after operaton", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException{
		Office responseOffice = service.findOneById(id).orElseThrow(() -> new ResourceNotFoundException("OfficeId: " + id + " was not found"));
		
		service.delete(responseOffice.getId());
		
		return ResponseEntity.ok().build();
	}

}
