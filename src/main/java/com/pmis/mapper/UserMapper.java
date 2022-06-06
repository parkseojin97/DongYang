package com.pmis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pmis.model.UserDTO;

public interface UserMapper extends DefaultDBInfo {

	
	// 회원 정보 생성 메서드 (비밀번호 암호화)
	@Insert("INSERT INTO " + USER + " VALUES (#{user_email},#{user_pw}, #{user_name}, now())")
	void insertUser(UserDTO user);

	// 회원 로그인 메서드
	@Select("SELECT (user_email, user_pw) FROM " + USER + " WHERE user_email=#{user_email}")
	String userLogin(UserDTO user);

	// 회원 조회 메서드
	@Select("SELECT * FROM " + USER + " WHERE user_email=#{user_email}")
	UserDTO getUser(UserDTO user);

	// 회원 이메일 중복 확인 메서드
	@Select("SELECT COUNT(*) FROM " + USER + " WHERE user_email=#{user_email}")
	int emailCheck(UserDTO user);

	// 게시판 댓글 작성 사용자 정보 조회 메서드(프로필 이미지, 닉네임)
	@Select("SELECT * FROM " + USER + " WHERE user_email=${user_email}")
	UserDTO getCommentUser(UserDTO user);

	// 회원 정보 수정 메서드
	@Update("UPDATE " + USER + " SET user_name=#{user_name} WHERE user_email=#{user_email}")
	void modifyAccount(UserDTO user);

	// 회원 탈퇴 메서드
	@Delete("DELETE FROM " + USER + " WHERE user_email=#{user_email}")
	void deleteAccount(UserDTO user);

	// 회원 비밀번호 변경 메서드
	@Update("UPDATE " + USER + " SET user_pw=#{user_pw} WHERE user_email=#{user_email}")
	void modifyPassword(UserDTO user);

	// 회원 프로필 이미지 변경 메서드
	@Update("UPDATE " + USER + " SET profile_image=#{img_url} where user_email=#{user_email}")
	void updateProfileImg(UserDTO user);
}
