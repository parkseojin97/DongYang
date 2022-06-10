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
		projectMapper.createProject(project);
		
		return true;
	}
	
	@Override
	public ProjectDTO selectLatestProject() {
		return projectMapper.selectProject();
	}

	public ArrayList<ProjectDTO> selectPagingProjects(int startIndex, int pageSize){
		
		return projectMapper.selectPagingProjects(startIndex,pageSize);
	}
	
	@Override
	public boolean deleteProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		projectMapper.deleteProject(project);
		return true;
	}

	@Override 
	public boolean createDefaultKanban(ProjectDTO project) {
		ProjectStatusDTO kanban = new ProjectStatusDTO();
		kanban.setProject_id(project.getProject_id());
		kanban.setProject_status("TO DO");		
		projectMapper.createBoardStatus(kanban);
		kanban.setProject_status("DOING");		
		projectMapper.createBoardStatus(kanban);
		kanban.setProject_status("DONE");		
		projectMapper.createBoardStatus(kanban);
		
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
		projectMapper.createBoardStatus(status);
		return true;
	}

	@Override
	public boolean deleteBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub
		projectMapper.deleteBoardStatus(status);
		return false;
	}

	@Override
	public ArrayList<ProjectBoardDTO> selectBoards(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.boards(project);
	}

	@Override
	public boolean createBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		projectMapper.createBoard(board);
		return false;
	}

	@Override
	public boolean deleteBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		projectMapper.deleteBoard(board);
		return false;
	}

	@Override
	public boolean updateBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		projectMapper.updateBoard(board);
		return false;
	}

	@Override
	public ArrayList<ProjectRuleDTO> selectRule(ProjectDTO project) {
		// TODO Auto-generated method stub
		return projectMapper.rule(project);
	}

	@Override
	public boolean createRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub
		projectMapper.createRule(rule);
		return false;
	}

	@Override
	public boolean deleteRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub
		projectMapper.deleteRule(rule);
		return false;
	}

	@Override
	public ArrayList<BoardCommentDTO> selectComment(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		return projectMapper.comment(board);
	}

	@Override
	public boolean createComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub
		projectMapper.createComment(comment);
		return false;
	}

	@Override
	public boolean deleteComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub
		projectMapper.deleteComment(comment);
		return false;
	}

	@Override
	public ArrayList<ProjectJoinDTO> selctGroup(ProjectDTO project) {
		// TODO Auto-generated method stub		
		return projectMapper.group(project);
	}

	@Override
	public boolean insertGroup(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		projectMapper.insertGroup(join);
		return true;
	}

	@Override
	public boolean deleteProjectUser(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		projectMapper.deleteGroup(join);
		return true;
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
		projectMapper.createMeeting(meeting);
		return true;
	}

	@Override
	public boolean deleteMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub
		projectMapper.deleteMeeting(meeting);
		return true;
	}

	@Override
	public ArrayList<MeetingLogDTO> selectMeetingLog(MeetingDTO meetingLog) {
		// TODO Auto-generated method stub		
		return projectMapper.meetingLog(meetingLog);
	}

	@Override
	public boolean insertMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		projectMapper.insertMeetingLog(meetingLog);
		return true;
	}

	@Override
	public boolean deleteMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		projectMapper.deleteMeetingLog(meetingLog);
		return true;
	}

	@Override
	public ArrayList<MeetingLogChatDTO> selectMeetingLogChat(MeetingLogDTO meetinglogDTO) {
		// TODO Auto-generated method stub		
		return projectMapper.meetingLogChat(meetinglogDTO);
	}

	@Override
	public boolean insertMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		projectMapper.insertMeetingLogChat(meetinglogDTO);
		return true;
	}

	@Override
	public boolean deleteMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		projectMapper.deleteMeetingChat(meetinglogDTO);
		return true;
	}

}
