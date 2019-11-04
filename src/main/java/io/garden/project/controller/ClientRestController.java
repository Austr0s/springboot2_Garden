package io.garden.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.garden.project.model.entity.Client;
import io.garden.project.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/client")
public class ClientRestController {

	@Autowired
	private ClientService service;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Find client by Id", notes = "Provide an id  to look up specific Client from Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Client"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})	
	public ResponseEntity<Optional<Client>> getOne(@PathVariable(value = "id") Long id) {
		
		Optional<Client> client = service.findOneById(id);
		
		return ResponseEntity.ok(client);
	}
	
	@GetMapping
	@ApiOperation(value = "Find all Clients", notes = "Returns all Clients from Api", response = Client.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Clients"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found")
	})
	public ResponseEntity<Iterable<Client>> getAll(){
		Iterable<Client> clients = service.findAll();
		
		return ResponseEntity.ok(clients);
	}
	
}
