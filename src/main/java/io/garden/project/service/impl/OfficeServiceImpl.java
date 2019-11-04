package io.garden.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.garden.project.model.entity.Office;
import io.garden.project.repository.OfficeRepository;
import io.garden.project.service.OfficeService;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	
	@Autowired
	private OfficeRepository repository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<Office> findOneById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Iterable<Office> findAll() {
		return repository.findAll();
	}

	@Override
	public Office create(Office entity) {
		return repository.save(entity);
	}

	@Override
	public Office update(Office entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
