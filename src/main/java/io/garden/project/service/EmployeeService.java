package io.garden.project.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.garden.project.model.entity.Employee;

public interface EmployeeService {
	
	Optional<Employee> findOneEmployee(Long employeeId);

	Optional<Employee> findByIdAndOfficeId(Long employeeId, Long officeId);

	Optional<Employee> findByIdAndBossEmployeeId(Long employeeId, Long bossId);

	Page<Employee> findByOfficeId(Long officeId, Pageable pageable);

	Page<Employee> findByBossEmployeeId(Long bossId, Pageable pageable);

	Page<Employee> findAll(Pageable pageable);

	Employee create(Employee employee);

	Employee update(Employee employee);

	void delete(Long id);

}
