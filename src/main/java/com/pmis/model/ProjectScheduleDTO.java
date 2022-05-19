package com.pmis.model;

import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ProjectScheduleDTO {
	private int schedule_id;
	private int board_id;
	private Date start_date;
	private Date final_date;
	private Date final_expect_date;
}
