package com.pmis.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmis.mapper.ProjectMapper;
import com.pmis.model.BoardCommentDTO;
import com.pmis.model.MeetingDTO;
import com.pmis.model.MeetingLogChatDTO;
import com.pmis.model.MeetingLogDTO;
import com.pmis.model.ProjectBoardDTO;
import com.pmis.model.ProjectBoardJoinKanban;
import com.pmis.model.ProjectDTO;
import com.pmis.model.ProjectJoinDTO;
import com.pmis.model.ProjectRuleDTO;
import com.pmis.model.ProjectStatusDTO;
import com.pmis.model.UserDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper; 
	
	@Override
	public ArrayList<ProjectDTO> selectProjects(UserDTO user) {
		// TODO Auto-generated method stub
		return projectMapper.selectProjects(user);
	}
	
	@Override
	public ArrayList<ProjectDTO> selectPublicProjects() {
		// TODO Auto-generated method stub
		return projectMapper.selectPublicProjects(); 
	}
	
	@Override
	public int selectPublicProjectCnt() {
		return projectMapper.selectPublicProjectCnt();
	}
	
	@Override	
	public boolean createProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		
		
		return projectMapper.createProject(project);
	}
	
	@Override
	// 프로젝트 조회
	public ProjectDTO selectOneProject(ProjectDTO project) {
		return projectMapper.selectOneProject(project);
	}
	@Override
	public ProjectDTO selectLatestProject() {
		return projectMapper.selectLatestProject();
	}

	public ArrayList<ProjectDTO> selectPagingProjects(int startIndex, int pageSize){
		
		return projectMapper.selectPagingProjects(startIndex,pageSize);
	}
	
	@Override
	public boolean deleteProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.deleteProject(project);
	}

	@Override 
	public boolean createDefaultKanban(ProjectDTO project) {
		ProjectStatusDTO kanban = new ProjectStatusDTO();
		kanban.setProject_id(project.getProject_id());
		kanban.setProject_status("TO DO");		
		if(!projectMapper.createBoardStatus(kanban)) return false;
		kanban.setProject_status("DOING");		
		if(!projectMapper.createBoardStatus(kanban)) return false;
		kanban.setProject_status("DONE");		
		if(!projectMapper.createBoardStatus(kanban)) return false;
		
		
		return true;
	}
	
	@Override
	public ArrayList<ProjectStatusDTO> selectBoardStatus(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.boardStatus(project);
	}

	@Override
	public boolean createBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub		
		return projectMapper.createBoardStatus(status);
	}

	@Override
	public boolean deleteBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteBoardStatus(status);
	}

	@Override
	public ArrayList<ProjectBoardDTO> selectBoards(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.boards(project);
	}

	@Override
	public boolean createBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub		
		return projectMapper.createBoard(board);
	}

	@Override
	public boolean deleteBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteBoard(board);
	}

	@Override
	public boolean updateBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub		
		return projectMapper.updateBoard(board);
	}
	
	@Override
	public ArrayList<ProjectBoardJoinKanban> selectBoardJoinKanban(UserDTO user){
		return projectMapper.selectBoardJoinKanban(user);
	}

	@Override
	public ArrayList<ProjectRuleDTO> selectRule(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.rule(project);
	}

	@Override
	public boolean createRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub		
		return projectMapper.createRule(rule);
	}

	@Override
	public boolean deleteRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteRule(rule);
	}

	@Override
	public ArrayList<BoardCommentDTO> selectComment(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		return projectMapper.comment(board);
	}

	@Override
	public boolean createComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub		
		return projectMapper.createComment(comment);
	}

	@Override
	public boolean deleteComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteComment(comment);
	}

	@Override
	public ProjectJoinDTO selctGroupCheck(ProjectDTO project, UserDTO user) {
		
		return projectMapper.selctGroupCheck(project, user);
	}
	
	@Override
	public ArrayList<ProjectJoinDTO> selctGroup(ProjectDTO project) {
		// TODO Auto-generated method stub		
		return projectMapper.group(project);
	}

	@Override
	public boolean insertGroup(ProjectJoinDTO join) {
		// TODO Auto-generated method stub		
		return projectMapper.insertGroup(join);
	}

	@Override
	public boolean deleteProjectUser(ProjectJoinDTO join) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteGroup(join);
	}
	
	@Override
	public boolean updateGroup(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		projectMapper.updateGroup(join);
		return true;
	}

	@Override
	public ArrayList<MeetingDTO> selectMeeting(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.meeting(project);
	}

	@Override
	public boolean createMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub		
		return projectMapper.createMeeting(meeting);
	}

	@Override
	public boolean deleteMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub
		
		return projectMapper.deleteMeeting(meeting);
	}

	@Override
	public ArrayList<MeetingLogDTO> selectMeetingLog(MeetingDTO meetingLog) {
		// TODO Auto-generated method stub		
		return projectMapper.meetingLog(meetingLog);
	}

	@Override
	public boolean insertMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		
		return projectMapper.insertMeetingLog(meetingLog);
	}

	@Override
	public boolean deleteMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteMeetingLog(meetingLog);
	}

	@Override
	public ArrayList<MeetingLogChatDTO> selectMeetingLogChat(MeetingLogDTO meetinglogDTO) {
		// TODO Auto-generated method stub		
		return projectMapper.meetingLogChat(meetinglogDTO);
	}

	@Override
	public boolean insertMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub		
		return projectMapper.insertMeetingLogChat(meetinglogDTO);
	}

	@Override
	public boolean deleteMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub		
		return projectMapper.deleteMeetingChat(meetinglogDTO);
	}

}
