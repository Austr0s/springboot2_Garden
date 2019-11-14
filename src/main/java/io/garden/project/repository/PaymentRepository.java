package io.garden.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import io.garden.project.model.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

	Optional<Payment> findByIdAndClientId(Long paymentId, Long clientId);

	Page<Payment> findByClientId(Long clientId, Pageable pageable);

}
