package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class UserDTO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private Date create_time;
	
}
