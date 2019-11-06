package io.garden.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.garden.project.model.entity.Client;
import io.garden.project.repository.ClientRepository;
import io.garden.project.service.ClientService;

@Service
@Transactional
public class ClientServiceIml implements ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<Client> findOneById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public 	Page<Client> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Client create(Client entity) {
		return repository.save(entity);
	}

	@Override
	public Client update(Client entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Long id) {		
		repository.deleteById(id);		
	}

}
