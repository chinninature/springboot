package com.user.service;

import java.util.List;

import com.user.dto.PostDTO;

public interface PostService {

	List<PostDTO> getAllPosts();

	PostDTO getPost(Long id) ;

	void savePost(PostDTO postDto) throws Exception;

	void updatePost(PostDTO postDto) throws Exception;

	void deletePost(Long id) throws Exception;

	List<PostDTO> getAllPostsWithUserName();


}
