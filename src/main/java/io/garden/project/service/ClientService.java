package io.garden.project.service;

import java.util.Optional;

import io.garden.project.model.entity.Client;

public interface ClientService {

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
	Iterable<Client> findAll();

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
