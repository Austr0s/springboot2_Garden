package io.garden.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.garden.project.model.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	Optional<Employee> findTopByOfficeId(Long officeId);

	Optional<Employee> findByIdAndOfficeId(Long employeeId, Long officeId);
	
	Optional<Employee> findByIdAndBossEmployeeId(Long employeeId, Long bossId);
	
	Page<Employee> findByOfficeId(Long officeId, Pageable pageable);
	
	Page<Employee> findByBossEmployeeId(Long bossId, Pageable pageable);
	
	Page<Employee> findAll(Pageable pageable);
		
}
