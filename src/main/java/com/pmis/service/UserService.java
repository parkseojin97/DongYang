package com.pmis.service;

import com.pmis.model.UserDTO;

public interface UserService {

	// 회원가입 회원 추가 메서드
	public boolean insert(UserDTO user);

	// 회원 로그인 메서드
	public String userLogin(UserDTO user);

	// 회원 전체 정보 조회 메서드
	public UserDTO getUser(UserDTO user);

	// 이메일 중복 확인 메서드
	public int emailCheck(UserDTO user);

	// 회원 닉네임 변경 메서드
	public boolean modifyAccount(UserDTO user);

	// 회원 탈퇴 메서드
	public boolean deleteAccount(UserDTO user);

	// 회원 비밀번호 변경 메서드
	public boolean modifyPassword(UserDTO user);

	// 회원 프로필 이미지 변경 메서드
	public void updateProfileImg(UserDTO user);
}
