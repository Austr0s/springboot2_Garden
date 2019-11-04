package io.garden.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.garden.project.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Query to get User by UserName
	 * 
	 * @param userName parameter to find on db.
	 * @return User finded.
	 */
	Optional<User> findByUserName(String userName);

}
