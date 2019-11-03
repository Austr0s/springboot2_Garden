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

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

	@Autowired
	private ClientService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Client>> getOne(@PathVariable(value = "id") Long id) {
		
		Optional<Client> client = service.findOneById(id);
		
		return ResponseEntity.ok(client);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Client>> getAll(){
		Iterable<Client> clients = service.findAll();
		
		return ResponseEntity.ok(clients);
	}
	
}
