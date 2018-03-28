package com.user.service;

import java.util.List;

import com.user.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();

	UserDTO getUser(Long id) ;

	void saveUser(UserDTO userDto) throws Exception;

	void updateUser(UserDTO userDto) throws Exception;

	void deleteUser(Long id) throws Exception;


}
