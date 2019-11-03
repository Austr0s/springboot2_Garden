package io.garden.project.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.garden.project.model.entity.Client;
import io.garden.project.repository.ClientRepository;
import io.garden.project.service.ClientService;

@Service
@Transactional
public class ClientServiceIml implements ClientService {
	
	@Autowired
	private ClientRepository repository;

	@Override
	public Optional<Client> findOneById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<Client> findAll() {
		return repository.findAll();
	}

}
