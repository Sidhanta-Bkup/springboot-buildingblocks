package com.spring.demo.hello;

public class UserDetaiuls {
	
	private String fName;
	private String lName;
	private String city;
	public UserDetaiuls(String fName, String lName, String city) {
		this.fName = fName;
		this.lName = lName;
		this.city = city;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "UserDetaiuls [fName=" + fName + ", lName=" + lName + ", city=" + city + "]";
	}
	
	
	
	

}
