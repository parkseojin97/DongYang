package com.pmis.model;

import lombok.Data;

@Data
public class ProjectJoinDTO {
	private int project_id;
	private String user_email;
	private String role;
	private String join_status;
}
