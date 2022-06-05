package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ProjectBoardDTO {
	private int board_id;
	private int project_id;
	private String board_subject;
	private String board_content;
	private Date board_create_date;
	private String create_user_id;
	private String start_user_id;
	private int views;
	private String project_status;
	private Date start_date;
	private Date final_date;
	private Date final_expect_date;
}
