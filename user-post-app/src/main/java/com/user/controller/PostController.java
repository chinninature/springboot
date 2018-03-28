package com.user.controller;
//eclipse

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.user.dto.PostDTO;
import com.user.service.PostService;

/**
 * This class help to handle Post CRUD  endpoints.
 * @author macwork
 *
 */
@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService postService;

	/**
	 * This method will return all posts
	 * @return List<PostDTO>
	 */
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllPosts() {
		List<PostDTO> postList = postService.getAllPosts();
		if (postList == null) {
			return new ResponseEntity<Error>(new Error("No data found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_name", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllPostsWithUserName() {
		List<PostDTO> postList = postService.getAllPostsWithUserName();
		if (postList == null) {
			return new ResponseEntity<Error>(new Error("No data found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PostDTO>>(postList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPost(@PathVariable("id") String str) {
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"), HttpStatus.NOT_FOUND);
		}
		PostDTO postDto = postService.getPost(id);
		if(postDto==null){
			return new ResponseEntity<Error>(new Error("Employee not found"),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PostDTO>(postDto,HttpStatus.OK);
		
	}
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> savePost(@RequestBody PostDTO postDto){
		try{
			postService.savePost(postDto);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.PUT)
	public ResponseEntity<?> updatePost(@RequestBody PostDTO postDto, @PathVariable("id") String str){
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"),HttpStatus.NOT_FOUND);
		}
		try{
			postDto.setId(id);
			postService.updatePost(postDto);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") String str){
		Long id;
		try{
			id=Long.parseLong(str);
		}catch(NumberFormatException e){
			return new ResponseEntity<Error>(new Error("Invalid id"), HttpStatus.NOT_FOUND);
		}
		try{
			postService.deletePost(id);
		}catch(Exception e){
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	class Error {
		private String message;

		public Error(String message) {
			super();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
