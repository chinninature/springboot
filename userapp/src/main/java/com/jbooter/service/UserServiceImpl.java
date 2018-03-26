package com.jbooter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jbooter.domain.User;
import com.jbooter.dto.UserDTO;
import com.jbooter.repository.UserRepository;

@Service("userService")

public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository;

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList = userRepository.findAll();
		if (!CollectionUtils.isEmpty(userList)) {
			List<UserDTO> dtoList = new ArrayList<UserDTO>();
			for (User user : userList) {
				dtoList.add(new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
						user.getGender(), user.getDob()));
			}
			return dtoList;
		}
		return null;
	}

	// @Override
	// public UserDTO getUser(String user) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public void saveUser(UserDTO userDto) throws Exception {
		User existuser = userRepository.findByEmail(userDto.getEmail());
		if (existuser != null) {
			throw new Exception("User already exist in database");
		}
		User userObj = new User(null, userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
				userDto.getGender(), userDto.getDob());
		userRepository.save(userObj);
	}

	@Override
	public void updateUser(UserDTO userDto) throws Exception {
		User user = userRepository.findByUserId(userDto.getId());
		if(user==null){
			throw new Exception("User not found");
		}
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName()); 
		user.setEmail(userDto.getEmail());
		user.setGender(userDto.getGender());
		user.setDob(userDto.getDob());
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		User user = userRepository.findByUserId(id);
		if(user==null){
			throw new Exception("user not found");
		}
		userRepository.delete(user);

	}

	@Override
	public UserDTO getUser(Long id) {
		User user = userRepository.findByUserId(id);
		UserDTO userDto = null;
		if (user != null) {
			userDto = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
					user.getGender(), user.getDob());
		}
		return userDto;
	}

}
