package com.pmis.model;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class BoardCommentDTO {
	private int project_board_board_id;
	private int comment_num;
	private String user_user_id;
	private String comment_content;
}
