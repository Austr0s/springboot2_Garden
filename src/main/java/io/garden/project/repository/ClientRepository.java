package io.garden.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.garden.project.model.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	Optional<Client> findTopByEmployeeId(Long employeeId);

	Page<Client> findAll(Pageable pageable);

}
