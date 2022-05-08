package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ProjectDTO {
	private int project_id;
	private String project_name;
	private String project_des;
	private int status_status_id;
	private Date project_create_date;
	private Date project_final_date;
	private Date project_final_expect_date;
}
