package io.garden.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.garden.project.model.entity.Payment;

public interface PaymentService {

	/**
	 * Find Entity by Id.
	 * 
	 * @param id of entity that we want to get data.
	 * @return Optional Entity
	 */
	Optional<Payment> findPaymentByClientIdAndPaymentId(Long clientId, Long paymentId);

	/**
	 * Find All Entity data.
	 * @param pageable 
	 * 
	 * @return List iterable of Entity.
	 */
	Page<Payment> findAllPaymentsByClientId(Long clientId, Pageable pageable);

	/**
	 * Create entity received.
	 * 
	 * @param entity that we want to create.
	 * @return entity created.
	 */
//	Payment create(Payment entity);

	/**
	 * Update entity received.
	 * 
	 * @param entity that we want to update.
	 * @return entity updated.
	 */
//	Payment update(Payment entity);

	/**
	 * Delete entity received.
	 * 
	 * @param id of entity that we want to delete.
	 */
//	void delete(Long id);
}
