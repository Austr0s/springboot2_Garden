package io.garden.project.service;

import java.util.Optional;

import io.garden.project.model.entity.Client;

public interface ClientService {
	
	Optional<Client> findOneById(Long id);
	
	Iterable<Client> findAll();

}
