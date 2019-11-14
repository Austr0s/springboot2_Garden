package io.garden.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import io.garden.project.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/clients")
@Api(value = "Client", tags = { "Client" })
public class ClientRestController {

	@Autowired
	private ClientService service;

	@GetMapping("/{id}")
	@ApiOperation(value = "Find client by Id", notes = "Provide an id  to look up specific Client from Api", response = Client.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Client"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found") })
	public ResponseEntity<Optional<Client>> getOne(@PathVariable(value = "id") Long id) {
		Optional<Client> client = service.findOneById(id);
		return (client.isPresent()) ? new ResponseEntity<>(client, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	@GetMapping
	@ApiOperation(value = "Find all Clients", notes = "Returns all Clients from Api", response = Client.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Clients"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found") })
	public Page<Client> findAllClients(Pageable pageable) {
		return service.findAll(pageable);
	}

	@PostMapping
	@ApiOperation(value = "Create a new Client", notes = "Returns new Client created and saved into Api", response = Client.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Saved Client"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found") })
	public ResponseEntity<Client> create(@RequestBody(required = true) Client office) {

		Client clientCreated = service.create(office);

		return ResponseEntity.ok(clientCreated);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update an existing Client", notes = "Returns Client updated and saved into Api", response = Client.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated Client"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found") })
	public ResponseEntity<Client> update(@RequestBody(required = true) Client office, @PathVariable Long id) {
		Optional<Client> clientOptional = service.findOneById(id);

		if (!clientOptional.isPresent())
			return ResponseEntity.notFound().build();

		office.setId(id);

		Client clientUpdated = service.update(office);

		return ResponseEntity.ok(clientUpdated);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete an existing Client", notes = "Returns nothing after operaton", response = Client.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  was not found") })
	public ResponseEntity<Optional<Client>> delete(@PathVariable Long id) {
		Optional<Client> clientOptional = service.findOneById(id);

		if (!clientOptional.isPresent())
			return ResponseEntity.notFound().build();

		service.delete(id);
		return ResponseEntity.ok(clientOptional);
	}

}
