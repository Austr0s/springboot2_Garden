package io.garden.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.garden.project.model.entity.Office;

public interface OfficeService {

	/**
	 * Find Entity by Id.
	 * 
	 * @param id of entity that we want to get data.
	 * @return Optional Entity
	 */
	Optional<Office> findOneById(Long id);

	/**
	 * Find All Entity data.
	 * 
	 * @return List iterable of Entity.
	 */
	Page<Office> findAll(Pageable pageable);

	/**
	 * Create entity received.
	 * 
	 * @param entity that we want to create.
	 * @return entity created.
	 */
	Office create(Office entity);

	/**
	 * Update entity received.
	 * 
	 * @param entity that we want to update.
	 * @return entity updated.
	 */
	Office update(Office entity);

	/**
	 * Delete entity received.
	 * 
	 * @param id of entity that we want to delete.
	 */
	void delete(Long id);

}
