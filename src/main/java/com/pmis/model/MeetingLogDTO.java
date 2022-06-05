package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class MeetingLogDTO {
	private int meeting_log_id;
	private int meeting_id;	
	private String meeting_log_subject;
	private String user_id;
	
}
