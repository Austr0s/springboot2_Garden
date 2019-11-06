package io.garden.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.garden.project.model.entity.Payment;
import io.garden.project.repository.PaymentRepository;
import io.garden.project.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository repository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<Payment> findPaymentByClientIdAndPaymentId(Long clientId, Long paymentId) {
		return repository.findByIdAndClientId(paymentId, clientId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Payment> findAllPaymentsByClientId(Long clientId, Pageable pageable) {
		return repository.findByClientId(clientId, pageable);
	}

//	@Override
//	public Payment create(Payment entity) {
//		return repository.save(entity);
//	}

//	@Override
//	public Payment update(Payment entity) {
//		return repository.save(entity);
//	}

//	@Override
//	public void delete(Long id) {
//		repository.deleteById(id);
//
//	}

}
