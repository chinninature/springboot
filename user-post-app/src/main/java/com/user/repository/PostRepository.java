package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.domain.User;
import com.user.domain.Post;
import com.user.dto.PostDTO;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("SELECT post FROM Post post WHERE id= :givenId")
	Post findByPostId(@Param("givenId") Long id);

	@Query("SELECT post FROM Post post WHERE name= :givenName")
	Post findByName(@Param("givenName") String name);

	@Query("SELECT post FROM Post post"
			+ " INNER JOIN FETCH post.user  user ORDER BY post.name ASC")
	List<Post> findAllPostsWithUser();

	@Query("SELECT new com.user.dto.PostDTO(post.id,post.name,post.description, u.name) "
			+ " FROM Post post INNER JOIN post.user  u")
	List<PostDTO> getAllPostsWithUserName();

	@Query("SELECT user FROM User user WHERE id= :givenId")
	User findUserByPostId(Long id);
}
