package com.jbooter.service.user;

import java.util.List;

import com.jbooter.dto.user.UserDTO;

public interface UserService {

	public List<UserDTO> getAllUsers();

	public void saveUser(UserDTO userDto) throws Exception;

	public void updateUser(UserDTO userDto) throws Exception;

	public void deleteUser(Long id) throws Exception;

	public UserDTO getUser(Long id);

}
