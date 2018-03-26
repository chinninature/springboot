package com.jbooter.service;

import java.util.List;

import com.jbooter.dto.UserDTO;

public interface UserService {

	public List<UserDTO> getAllUsers();

	public void saveUser(UserDTO userDto) throws Exception;

	public void updateUser(UserDTO userDto) throws Exception;

	public void deleteUser(Long id) throws Exception;

	public UserDTO getUser(Long id);

}
