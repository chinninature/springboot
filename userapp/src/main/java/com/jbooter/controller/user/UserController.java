package com.jbooter.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jbooter.dto.user.UserDTO;
import com.jbooter.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> dtoList = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(dtoList, HttpStatus.OK);

	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDto) {
		try {
			userService.saveUser(userDto);
		} catch (Exception e) {
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") String str) {
		Long id;
		try {
			id = Long.parseLong(str);
		} catch (NumberFormatException e) {
			return new ResponseEntity<Error>(new Error("Invalid user id"), HttpStatus.NOT_FOUND);
		}
		UserDTO userDto = userService.getUser(id);
		if (userDto == null) {
			return new ResponseEntity<Error>(new Error("User not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDto, @PathVariable("id") String str) {
		Long id;
		try {
			id = Long.parseLong(str);
		} catch (NumberFormatException e) {
			return new ResponseEntity<Error>(new Error("Invalid user id"), HttpStatus.NOT_FOUND);
		}
		try {
			userDto.setId(id);
			userService.updateUser(userDto);
		} catch (Exception e) {
			return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String str) {
		Long id;
		try {
			id = Long.parseLong(str);
		} catch (NumberFormatException e) {
			return new ResponseEntity<Error>(new Error("Invalid user id"), HttpStatus.NOT_FOUND);
		}
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
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
