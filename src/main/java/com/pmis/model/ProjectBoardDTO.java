package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ProjectBoardDTO {
	private int project_project_id;
	private int board_id;
	private String board_subject;
	private String board_content;
	private Date board_create_date;
	private Date board_start_date;
	private Date board_end_date;
	private String user_user_start_id;
	private String user_user_create_id;
	private int status_status_id;
}
