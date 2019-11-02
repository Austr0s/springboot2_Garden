package io.garden.project.repository;

import org.springframework.data.repository.CrudRepository;

import io.garden.project.model.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

}
