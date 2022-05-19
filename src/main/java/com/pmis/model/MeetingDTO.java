package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class MeetingDTO {
	private int meeting_id;
	private String meeting_title;
	private Date meeting_start_date;
	private Date meeting_end_date;
	private int project_id;
	
}
