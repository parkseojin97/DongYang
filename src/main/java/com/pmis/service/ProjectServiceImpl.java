package com.pmis.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

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

public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper proejctMapper; 
	
	@Override
	public ArrayList<ProjectDTO> selectProjects(UserDTO user) {
		// TODO Auto-generated method stub
		return proejctMapper.selectProjects(user);
	}

	@Override
	public boolean createProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		proejctMapper.createProject(project);
		return true;
	}

	@Override
	public boolean deleteProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		proejctMapper.deleteProject(project);
		return true;
	}

	@Override
	public ArrayList<ProjectStatusDTO> selectBoardStatus(ProjectDTO project) {
		// TODO Auto-generated method stub
		return proejctMapper.boardStatus(project);
	}

	@Override
	public boolean createBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub
		proejctMapper.createBoardStatus(status);
		return true;
	}

	@Override
	public boolean deleteBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub
		proejctMapper.deleteBoardStatus(status);
		return false;
	}

	@Override
	public ArrayList<ProjectBoardDTO> selectBoards(ProjectDTO proejct) {
		// TODO Auto-generated method stub
		proejctMapper.boards(proejct);
		return null;
	}

	@Override
	public boolean createBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		proejctMapper.createBoard(board);
		return false;
	}

	@Override
	public boolean deleteBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		proejctMapper.deleteBoard(board);
		return false;
	}

	@Override
	public boolean updateBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		proejctMapper.updateBoard(board);
		return false;
	}

	@Override
	public ArrayList<ProjectRuleDTO> selectRule(ProjectDTO project) {
		// TODO Auto-generated method stub
		proejctMapper.rule(project);
		return null;
	}

	@Override
	public boolean createRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub
		proejctMapper.createRule(rule);
		return false;
	}

	@Override
	public boolean deleteRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub
		proejctMapper.deleteRule(rule);
		return false;
	}

	@Override
	public ArrayList<BoardCommentDTO> selectComment(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		proejctMapper.comment(board);
		return null;
	}

	@Override
	public boolean createComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub
		proejctMapper.createComment(comment);
		return false;
	}

	@Override
	public boolean deleteComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub
		proejctMapper.deleteComment(comment);
		return false;
	}

	@Override
	public ArrayList<ProjectJoinDTO> selctGroup(ProjectDTO proejct) {
		// TODO Auto-generated method stub		
		return proejctMapper.group(proejct);
	}

	@Override
	public boolean inviteUser(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		proejctMapper.insertGroup(join);
		return true;
	}

	@Override
	public boolean deleteProjectUser(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		proejctMapper.deleteGroup(join);
		return true;
	}

	@Override
	public ArrayList<MeetingDTO> selectMeeting(ProjectDTO proejct) {
		// TODO Auto-generated method stub
		return proejctMapper.meeting(proejct);
	}

	@Override
	public boolean createMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub
		proejctMapper.createMeeting(meeting);
		return true;
	}

	@Override
	public boolean deleteMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub
		proejctMapper.deleteMeeting(meeting);
		return true;
	}

	@Override
	public ArrayList<MeetingLogDTO> selectMeetingLog(MeetingDTO meetingLog) {
		// TODO Auto-generated method stub		
		return proejctMapper.meetingLog(meetingLog);
	}

	@Override
	public boolean insertMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		proejctMapper.insertMeetingLog(meetingLog);
		return true;
	}

	@Override
	public boolean deleteMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		proejctMapper.deleteMeetingLog(meetingLog);
		return true;
	}

	@Override
	public ArrayList<MeetingLogChatDTO> selectMeetingLogChat(MeetingLogDTO meetinglogDTO) {
		// TODO Auto-generated method stub		
		return proejctMapper.meetingLogChat(meetinglogDTO);
	}

	@Override
	public boolean insertMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		proejctMapper.insertMeetingLogChat(meetinglogDTO);
		return true;
	}

	@Override
	public boolean deleteMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		proejctMapper.deleteMeetingChat(meetinglogDTO);
		return true;
	}

}
