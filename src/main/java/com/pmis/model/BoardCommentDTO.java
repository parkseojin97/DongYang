package com.pmis.model;

import lombok.Data;

@Data
public class BoardCommentDTO {
	private int comment_id;
	private int board_id;
	private String user_email;
	private String comment_content;
}
