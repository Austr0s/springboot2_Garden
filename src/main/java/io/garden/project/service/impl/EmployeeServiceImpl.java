package io.garden.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.garden.project.model.entity.Employee;
import io.garden.project.repository.EmployeeRepository;
import io.garden.project.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Optional<Employee> findTopByOfficeId(Long officeId) {
		return repository.findTopByOfficeId(officeId);
	}

	@Override
	public Optional<Employee> findOneEmployee(Long employeeId) {
		return repository.findById(employeeId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<Employee> findByIdAndOfficeId(Long employeeId, Long officeId) {
		return repository.findByIdAndOfficeId(employeeId, officeId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<Employee> findByIdAndBossEmployeeId(Long employeeId, Long bossId) {
		return repository.findByIdAndBossEmployeeId(employeeId, bossId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Employee> findByOfficeId(Long officeId, Pageable pageable) {
		return repository.findByOfficeId(officeId, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Employee> findByBossEmployeeId(Long bossId, Pageable pageable) {
		return repository.findByBossEmployeeId(bossId, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Employee> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Employee create(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Employee update(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
