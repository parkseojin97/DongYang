package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class MeetingLogChatDTO {
	private int meeting_log_id;
	private String meeting_log_opinion;
	private String user_id;
	
}
