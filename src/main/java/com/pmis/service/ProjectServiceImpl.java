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

public class ProjectServiceImpl implements ProjectService {

	@Override
	public ArrayList<ProjectDTO> selectProjects(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProject(ProjectDTO project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ProjectStatusDTO> selectBoardStatus(ProjectDTO project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBoardStatus(ProjectStatusDTO status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ProjectBoardDTO> selectBoards(ProjectDTO proejct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBoard(ProjectStatusDTO status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBoard(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ProjectRuleDTO> selectRule(ProjectDTO project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRule(ProjectRuleDTO rule) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BoardCommentDTO> selectComment(ProjectBoardDTO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteComment(BoardCommentDTO comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ProjectJoinDTO> selctGroup(ProjectDTO proejct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inviteUser(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProjectUser(ProjectJoinDTO join) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MeetingDTO> selectMeeting(ProjectDTO proejct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMeeting(MeetingDTO meeting) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MeetingLogDTO> selectMeetingLog(MeetingDTO meetingLog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMeetingLog(MeetingLogDTO meetingLog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MeetingLogChatDTO> selectMeetingLogChat(MeetingLogDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMeetingChat(MeetingLogChatDTO meetinglogDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
