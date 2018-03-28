package com.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PostDTO {
	private Long id;
	private String name;
	private String description;
	private UserDTO user;

	public PostDTO() {
		super();
	}

	public PostDTO(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public PostDTO(Long id, String name, String description, UserDTO user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
	}

	public PostDTO(Long id, String name, String description, String userName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = new UserDTO(userName);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ProjectDTO [id=" + id + ", name=" + name + ", description=" + description + ", user=" + user
				+ "]";
	}

}
