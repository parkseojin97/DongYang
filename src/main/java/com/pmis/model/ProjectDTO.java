package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectDTO {
	private int project_id;
	private String project_name;
	private String project_des;
	private Date project_create_date;
	private Date project_final_date;
	private Date project_final_expect_date;
	private String privacy_scope;

}
