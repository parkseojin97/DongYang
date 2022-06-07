package com.pmis.service;

import java.util.ArrayList;

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


public interface ProjectService {
	
	// 프로젝트
	
	// 프로젝트 목록 불러오기
	public ArrayList<ProjectDTO> selectProjects(UserDTO user);
	
	// 모든 프로젝트 목록 불러오기
	public ArrayList<ProjectDTO> selectProjects();
	
	// 새로운 프로젝트 생성
	public boolean createProject(ProjectDTO project); 
	
	// 프로젝트 삭제
	public boolean deleteProject(ProjectDTO project);
	
	// 프로젝트 조회
	public ProjectDTO selectLatestProject();
	
	// default칸반 TO DO, DOING, DONE 칸반 생성
	public boolean createDefaultKanban(ProjectDTO project);
	
	// task
	// task 종류(칸반) 불러오기
	public ArrayList<ProjectStatusDTO> selectBoardStatus(ProjectDTO project);
	
	// 새로운 칸반 생성
	public boolean createBoardStatus(ProjectStatusDTO status);
	
	// 칸반 삭제
	public boolean deleteBoardStatus(ProjectStatusDTO status);
	
	
	// 프로젝트 task(board) 불러오기 
	public ArrayList<ProjectBoardDTO> selectBoards(ProjectDTO project);		
	
	// 새로운 task(board) 생성
	public boolean createBoard(ProjectBoardDTO board);		
	
	// task 삭제
	public boolean deleteBoard(ProjectBoardDTO board);		
	
	// task 작업 내용 수정
	public boolean updateBoard(ProjectBoardDTO board);
	
	
	
	// 프로젝트 룰
	// 프로젝트 룰 불러오기
	public ArrayList<ProjectRuleDTO> selectRule(ProjectDTO project);
	
	// 프로젝트 룰 생성
	public boolean createRule(ProjectRuleDTO rule);
	
	// 프로젝트 룰 삭제
	public boolean deleteRule(ProjectRuleDTO rule);


	// 댓글
	// 태스크별 댓글 불러오기
	public ArrayList<BoardCommentDTO> selectComment(ProjectBoardDTO board);
	
	// 댓글 입력
	public boolean createComment(BoardCommentDTO comment); 
	
	// 댓글 삭제
	public boolean deleteComment(BoardCommentDTO comment);
	
	
	// 그룹
	// 그룹원 목록 불러오기
	public ArrayList<ProjectJoinDTO> selctGroup(ProjectDTO project);
	
	// 그룹원 추가
	public boolean insertGroup(ProjectJoinDTO join); 
	
	// 그룹원 삭제
	public boolean deleteProjectUser(ProjectJoinDTO join);
	
	// 그룹원 요청 처리
	public boolean updateGroup(ProjectJoinDTO join);
	
	// 회의
	// 회의 목록 불러오기
	public ArrayList<MeetingDTO> selectMeeting(ProjectDTO project);
	
	// 미팅 일정 생성
	public boolean createMeeting(MeetingDTO meeting);
	
	// 회의 정보
	public boolean deleteMeeting(MeetingDTO meeting);
	
	// 회의 로그 불러오기
	public ArrayList<MeetingLogDTO> selectMeetingLog(MeetingDTO meetingLog);
	
	// 회의 주제 추가
	public boolean insertMeetingLog(MeetingLogDTO meetingLog);
	
	// 회의 주제 삭제
	public boolean deleteMeetingLog(MeetingLogDTO meetingLog);
	
	// 주제에대한 의견 불러오기
	public ArrayList<MeetingLogChatDTO> selectMeetingLogChat(MeetingLogDTO meetinglogDTO);
	
	// 주제에 대한 의견 생성
	public boolean insertMeetingChat(MeetingLogChatDTO meetinglogDTO);
	
	// 의견 삭제
	public boolean deleteMeetingChat(MeetingLogChatDTO meetinglogDTO);
	
	

}
