package io.garden.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.garden.project.model.entity.Client;

public interface ClientService {
	
	
	Optional<Client> findTopByEmployeeId(Long employeeId);

	/**
	 * Find Entity by Id.
	 * 
	 * @param id of entity that we want to get data.
	 * @return Optional Entity
	 */
	Optional<Client> findOneById(Long id);

	/**
	 * Find All Entity data.
	 * 
	 * @return List iterable of Entity.
	 */
	Page<Client> findAll(Pageable pageable);

	/**
	 * Create entity received.
	 * 
	 * @param entity that we want to create.
	 * @return entity created.
	 */
	Client create(Client entity);

	/**
	 * Update entity received.
	 * 
	 * @param entity that we want to update.
	 * @return entity updated.
	 */
	Client update(Client entity);

	/**
	 * Delete entity received.
	 * 
	 * @param id of entity that we want to delete.
	 */
	void delete(Long id);

}
