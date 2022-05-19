package com.pmis.model;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class BoardCommentDTO {
	private int comment_id;
	private int board_id;
	private String user_id;
	private String comment_content;
}
