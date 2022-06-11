package com.pmis.model;

import java.util.Date;

import lombok.Data;
@Data
public class ProjectBoardJoinUserDTO {
	private int board_id;
	private int project_id;
	private String board_subject;
	private String board_content;
	private Date board_create_date;
	private String create_user_email;
	private String start_user_email;
	private int views;
	private Date start_date;
	private Date final_date;
	private Date final_expect_date;
	private int kanban_id;
	private String create_user_name;
	private String start_user_name;
}
