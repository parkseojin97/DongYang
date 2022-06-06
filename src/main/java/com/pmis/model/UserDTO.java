package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	private String user_email;
	private String user_pw;
	private String user_name;
	private Date create_time;
	
}
