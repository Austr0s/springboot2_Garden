package io.garden.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.garden.project.model.entity.Office;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {

	Page<Office> findAll(Pageable pageable);

}
