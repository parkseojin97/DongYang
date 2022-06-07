package com.pmis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pmis.model.BoardCommentDTO;
import com.pmis.model.MeetingDTO;
import com.pmis.model.MeetingLogChatDTO;
import com.pmis.model.MeetingLogDTO;
import com.pmis.model.ProjectBoardDTO;
import com.pmis.model.ProjectDTO;
import com.pmis.model.ProjectJoinDTO;
import com.pmis.model.ProjectRuleDTO;
import com.pmis.model.ProjectStatusDTO;
import com.pmis.model.UserDTO;

public interface ProjectMapper extends DefaultDBInfo {
	
	// 프로젝트 
	// 참여 프로젝트 목록 불러오기
	@Select("SELECT p.project_id, project_name, project_des, project_create_date, project_final_date, project_final_expect_date, status_id, privacy_scope"
			+ " FROM " + PROJECT + " p, " + PROJECTJOIN + " pj WHERE p.project_id = pj.project_id and pj.user_email = #{user_email};")
	ArrayList<ProjectDTO> selectProjects(UserDTO user);
	
	// 모든 프로젝트 목록 불러오기
	@Select("SELECT * FROM " + PROJECT )
	ArrayList<ProjectDTO> selectAllProjects();
	
	// 새로운 프로젝트 생성
	@Insert("INSERT INTO " + PROJECT + " VALUES( null, #{project_name}, #{project_des}, "
			+ "now(), #{project_final_date}, #{proejct_final_expect_date}, #{status_id}, #{privacy_scope_id} )")
	void createProject(ProjectDTO project);
	
	// 프로젝트 삭제
	@Delete("DELETE FROM " + PROJECT + " WHERE project_id=#{project_id}")
	void deleteProject(ProjectDTO project);
	
	// task
	// task 종류(칸반) 불러오기
	@Select("SELECT * FROM " + PROJECTSTATUS + " where project_id=#{project_id}")
	ArrayList<ProjectStatusDTO> boardStatus(ProjectDTO project);	
	
	// 새로운 칸반 생성
	@Insert("INSERT INTO " + PROJECTSTATUS + " VALUES( #{project_status}, #{project_id} ) ")
	void createBoardStatus(ProjectStatusDTO status);
	
	// 칸반 삭제
	@Delete("DELETE FROM " + PROJECTBOARD + " WHERE project_id=#{project_id} and project_status=#{project_status}")
	void deleteBoardStatus(ProjectStatusDTO status);
	
	// 프로젝트 task(board) 불러오기 
	@Select("SELECT * FROM " + PROJECTBOARD + " where project_id=#{project_id} ORDER BY board_id DESC")
	ArrayList<ProjectBoardDTO> boards(ProjectDTO project);
	
	// 새로운 task(board) 생성
	@Insert("INSERT INTO " + PROJECTBOARD
			+ " VALUES(null,#{project_id},#{board_subject},#{board_content},now(),#{create_user_email}, #{start_user_email}, 0"
			+ "#{project_status}, #{start_date}, #{final_date}, #{final_expect_date})")
	void createBoard(ProjectBoardDTO board);

	// task 삭제
	@Delete("DELETE FROM " + PROJECTBOARD + " WHERE board_id=#{board_id}")
	void deleteBoard(ProjectBoardDTO board);	
	
	// 작업 내용 수정
	@Update("UPDATE " + PROJECTBOARD
			+ " board_subject=#{board_subject}, board_content=#{board_content}, start_user_email=#{start_user_email},"
			+ "views=#{views}, project_status=#{project_status}, start_date=#{start_date}, final_date=#{final_date}, final_expect_date=#{final_expect_date}"
			+ " WHERE board_id=#{board_id}")
	void updateBoard(ProjectBoardDTO board);
	
	// 프로젝트 룰
	// 프로젝트 룰 불러오기
	@Select("SELECT * FROM " + PROJECTRULE + " WHERE project_id=#{project_id}")
	ArrayList<ProjectRuleDTO> rule(ProjectDTO project);
	
	// 프로젝트 룰 생성
	@Insert("INSERT INTO " + PROJECTRULE + " VALUES (#{project_id} ,#{rule}) ")
	void createRule(ProjectRuleDTO rule);
	
	// 프로젝트 룰 삭제
	@Delete("DELETE FROM " + PROJECTRULE + " WHERE project_id=#{project_id} and rule=#{rule}")
	void deleteRule(ProjectRuleDTO rule);	
	
	// 댓글
	// 태스크별 댓글 불러오기
	@Select("Select * FROM " + BOARDCOMMENT + " where board_id=#{board_id} ")
	ArrayList<BoardCommentDTO> comment (ProjectBoardDTO board);
	
	// 댓글 입력
	@Insert("INSERT INTO " + BOARDCOMMENT + " VALUES (null, #{board_id} ,#{user_email}, #{comment_content}) ")
	void createComment(BoardCommentDTO comment); 
	
	// 댓글 삭제
	@Delete("DELETE FROM " + BOARDCOMMENT + " WHERE comment_id=#{comment_id}")
	void deleteComment(BoardCommentDTO comment);
	
	// 그룹원 목록 불러오기
	@Select("SELECT * FROM " + PROJECTJOIN + " where project_id=#{project_id}") 
	ArrayList<ProjectJoinDTO> group(ProjectDTO project);
	
	// 그룹원 추가하기
	@Insert("INSERT INTO " + PROJECTJOIN + " VALUES (#{project_id}, #{user_email}, #{role}, #{join_status} )")
	void insertGroup(ProjectJoinDTO join);
	
	// 그룹원 삭제
	@Delete("DELETE FROM " + PROJECTJOIN + " WHERE project_id=#{project_id} and user_email=#{user_email}")
	void deleteGroup(ProjectJoinDTO join);
	
	// 그룹원 요청 처리
	@Update("UPDATE " + PROJECTJOIN + "join_status=#{join_status} WHERE user_email=#{user_email}")		
	void updateGroup(ProjectJoinDTO join);
	
	// 회의
	// 호의 목록 불러오기
	@Select("SELECT * FROM " + MEETING + " WHERE project_id=#{project_id}")
	ArrayList<MeetingDTO> meeting(ProjectDTO project);
	
	// 회의 일정 생성
	@Insert("INSERT INTO " + MEETING
			+ " VALUES (null, #{meeting_title}, #{meeting_start_date}, #{meeting_end_date}, #{project_id})")
	void createMeeting(MeetingDTO meeting);
	
	// 회의 정보 삭제
	@Delete("DELETE FROM " + MEETING + " WHERE meeting_id=#{meeting_id}")
	void deleteMeeting(MeetingDTO meeting);
	
	
	// 회의 주제 불러오기
	@Select("SELECT * FROM " + MEETINGLOG + " WHERE meeting_id=#{meeting_id}")
	ArrayList<MeetingLogDTO> meetingLog(MeetingDTO meetingLog);
	
	// 회의 주제 추가
	@Insert("INSERT INTO " + MEETINGLOG + " VALUES (null, #{meeting_id}, #{meeting_log_subject}, #{user_email})")
	void insertMeetingLog(MeetingLogDTO meetingLog);
	
	// 회의 주제 삭제
	@Delete("DELETE FROM " + MEETINGLOG + " WHERE meeting_log_id=#{meeting_log_id}")
	void deleteMeetingLog(MeetingLogDTO meetingLog);
	
	// 주제에대한 의견 불러오기
	@Select("SELECT * FROM " + MEETINGLOGCHAT + " WHERE meeting_log_id=#{meeting_log_id} ")
	ArrayList<MeetingLogChatDTO> meetingLogChat(MeetingLogDTO meetinglogDTO);
	
	// 주제에대한 의견 생성
	@Insert("INSERT INTO " + MEETINGLOGCHAT + " VALUES (null, #{meeting_log_id}, #{meeting_log_opinion}, #{user_email})")
	ArrayList<MeetingLogChatDTO> insertMeetingLogChat(MeetingLogChatDTO meetinglogDTO);
	
	// 회의 주제 삭제
	@Delete("DELETE FROM " + MEETINGLOGCHAT + " WHERE meeting_log_chat_id=#{meeting_log_chat_id}")
	void deleteMeetingChat(MeetingLogChatDTO meetingLog);
		

}
