package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.user.domain.User;
import com.user.dto.UserDTO;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList = userRepository.findAll();
		if (!CollectionUtils.isEmpty(userList)) {
			List<UserDTO> dtoList = new ArrayList<UserDTO>();
			for (User user : userList) {
				dtoList.add(new UserDTO(user.getId(), user.getName(), user.getAddress(), user.getSalary(),
						user.getCompany()));
			}
			return dtoList;
		}
		return null;
	}

	@Override
	public UserDTO getUser(Long id){

		User user = userRepository.findByUserId(id);
		UserDTO userDto = null;
		if (user != null) {
			userDto = new UserDTO(user.getId(), user.getName(), user.getAddress(), user.getSalary(), user.getCompany());
		}
		return userDto;
	}

	@Override
	public void saveUser(UserDTO userDto) throws Exception {
		User user = userRepository.findByName(userDto.getName());
		if(user!=null){
			throw new Exception("User already exists");
		}
		User userObj = new User(null,userDto.getName(), userDto.getAddres(), userDto.getSalary(), userDto.getCompany());
		userRepository.save(userObj);
	}

	@Override
	public void updateUser(UserDTO userDto) throws Exception {
		User user = userRepository.findByUserId(userDto.getId());
		if(user==null){
			throw new Exception("User not found");
		}
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setAddress(userDto.getAddres());
		user.setSalary(userDto.getSalary());
		user.setCompany(userDto.getCompany());
		userRepository.save(user);
		
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		User user = userRepository.findByUserId(id);
		if(user==null){
			throw new Exception("User not found");
		}
		userRepository.delete(user);
		
	}
	
	
	
	

}
