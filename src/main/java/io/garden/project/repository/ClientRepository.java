package io.garden.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.garden.project.model.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{

}
