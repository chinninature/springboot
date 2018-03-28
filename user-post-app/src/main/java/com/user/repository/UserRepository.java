package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user WHERE id= :givenId")
	User findByUserId(@Param("givenId") Long id);
	
	@Query("SELECT user FROM User user WHERE name= :givenName")
	User findByName(@Param("givenName") String name);
}
