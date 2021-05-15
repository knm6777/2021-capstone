package com.example.demo.repository.user;

import com.example.demo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsById(String id);

	Boolean existsByEmail(String email);

	Optional<User> findById(String id);

	User findAllById(String id);

}