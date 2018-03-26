package com.jbooter.repository.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jbooter.domain.user.User;

@Repository
@Qualifier("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT user FROM User user WHERE email= :emailString")
	User findByEmail(@Param("emailString") String email);
	
	@Query("SELECT user FROM User user WHERE id= :userId")
	User findByUserId(@Param("userId")Long id);
	
	
	

}
