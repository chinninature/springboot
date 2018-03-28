package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.user.domain.User;
import com.user.domain.Post;
import com.user.dto.UserDTO;
import com.user.dto.PostDTO;
import com.user.repository.UserRepository;
import com.user.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired 
	UserRepository userRepository;

	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> postList = postRepository.findAllPostsWithUser();
		if (!CollectionUtils.isEmpty(postList)) {
			List<PostDTO> dtoList = new ArrayList<PostDTO>();
			for (Post post : postList) {
				UserDTO userDto = null;
				if (post.getUser() != null) {
					User user = post.getUser();
					userDto = new UserDTO(user.getId(), user.getName(), user.getAddress(), user.getSalary(),
							user.getCompany());
				}
				dtoList.add(new PostDTO(post.getId(), post.getName(), post.getDescription(), userDto));
			}
			return dtoList;
		}
		return null;
	}
	
	@Override
	public List<PostDTO> getAllPostsWithUserName() {
		List<PostDTO> dtoList = postRepository.getAllPostsWithUserName();
		return dtoList;
	}

	@Override
	public PostDTO getPost(Long id) {

		Post post = postRepository.findByPostId(id);
		UserDTO userDto = null;
		if (post.getUser() != null) {
			User user = post.getUser();
			userDto = new UserDTO(user.getId(), user.getName(), user.getAddress(), user.getSalary(),
					user.getCompany());
		}
		PostDTO postDto = new PostDTO(post.getId(), post.getName(), post.getDescription(), userDto);
		return postDto;
	}

	@Override
	public void savePost(PostDTO postDto) throws Exception {
		User user = null;
		if(postDto.getUser().getId()==null || postDto.getUser().getId()==0){
			user = new User(null, postDto.getUser().getName(),
					postDto.getUser().getAddres(), postDto.getUser().getSalary(),
					postDto.getUser().getCompany());
		}else{
			user = userRepository.findByUserId(postDto.getUser().getId());
		}
		
		Post postObj = new Post(null, postDto.getName(), postDto.getDescription(),user);
		postRepository.save(postObj);
	}

	@Override
	public void updatePost(PostDTO postDto) throws Exception {
		Post post = postRepository.findByPostId(postDto.getId());
		if (post == null) {
			throw new Exception("Post not found");
		}
		post.setName(postDto.getName());
		post.setDescription(postDto.getDescription());
		postRepository.save(post);

	}

	@Override
	public void deletePost(Long id) throws Exception {
		Post post = postRepository.findByPostId(id);
		if (post == null) {
			throw new Exception("Post not found");
		}
		postRepository.delete(post);
	}

}
