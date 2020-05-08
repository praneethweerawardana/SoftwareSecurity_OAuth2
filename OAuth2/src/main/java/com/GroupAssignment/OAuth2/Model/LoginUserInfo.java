package com.GroupAssignment.OAuth2.Model;

public class LoginUserInfo {

	//user data model
	
	public String firstName;
	public String lastName;
	public String imageUrl;
	
	public LoginUserInfo() {}
	
	public LoginUserInfo(String firstName, String lastName, String imageUrl) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
