package com.pmis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmis.mapper.UserMapper;
import com.pmis.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean insert(UserDTO user) {
		userMapper.insertUser(user);
		return true;
	}

	@Override
	public String userLogin(UserDTO user) {
		// TODO Auto-generated method stub
		return userMapper.userLogin(user);
	}

	@Override
	public UserDTO getUser(UserDTO user) {
		// TODO Auto-generated method stub
		return userMapper.getUser(user);
	}

	@Override
	public int emailCheck(UserDTO user) {
		// TODO 중복 이메일 검사
		return userMapper.emailCheck(user);
	}

	@Override
	public boolean modifyAccount(UserDTO user) {
		// TODO 계정 정보 수정
		userMapper.modifyAccount(user);
		return true;
	}

	@Override
	public boolean deleteAccount(UserDTO user) {
		// TODO 계정 삭제
		userMapper.deleteAccount(user);
		return true;
	}

	@Override
	public boolean modifyPassword(UserDTO user) {
		// TODO 회원 비밀번호 변경
		userMapper.modifyPassword(user);
		return true;
	}

	@Override
	public void updateProfileImg(UserDTO user) {
		// TODO cloudinary에 업로드된 이미지 경로를 DB에 저장하여 CDN으로 활용
		userMapper.updateProfileImg(user);
	}

}
