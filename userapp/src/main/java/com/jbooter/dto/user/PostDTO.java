package com.jbooter.dto.user;

public class PostDTO {

	private Long id;
	private String title;
	private String description;
	private UserDTO userDto;

	public PostDTO(Long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public PostDTO(Long id, String title, String description, UserDTO userDto) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.userDto = userDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getUser() {
		return userDto;
	}

	public void setUser(UserDTO userDto) {
		this.userDto = userDto;
	}

}
