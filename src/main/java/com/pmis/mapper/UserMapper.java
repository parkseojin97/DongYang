package com.pmis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pmis.model.UserDTO;

public interface UserMapper extends DefaultDBInfo {

	// 회원 정보 생성 메서드 (비밀번호 암호화)
	@Insert("INSERT INTO " + USER + "(user_id, user_pw, user_name) VALUES (#{user_id},#{user_pw}, #{user_name})")
	void insertUser(UserDTO user);

	// 회원 로그인 메서드
	@Select("SELECT (user_id, user_pw) FROM " + USER + " WHERE user_id=#{user_id}")
	String userLogin(UserDTO user);

	// 회원 조회 메서드
	@Select("SELECT * FROM " + USER + " WHERE user_id=#{user_id}")
	UserDTO getUser(UserDTO user);

	// 회원 이메일 중복 확인 메서드
	@Select("SELECT COUNT(*) FROM " + USER + " WHERE user_id=#{user_id}")
	int emailCheck(UserDTO user);

	// 게시판 댓글 작성 사용자 정보 조회 메서드(프로필 이미지, 닉네임)
	@Select("SELECT * FROM " + USER + " WHERE user_id=${user_id}")
	UserDTO getCommentUser(UserDTO user);

	// 회원 정보 수정 메서드
	@Update("UPDATE " + USER + " SET user_name=#{user_name} WHERE user_id=#{user_id}")
	void modifyAccount(UserDTO user);

	// 회원 탈퇴 메서드
	@Delete("DELETE FROM " + USER + " WHERE user_id=#{user_id}")
	void deleteAccount(UserDTO user);

	// 회원 비밀번호 변경 메서드
	@Update("UPDATE " + USER + " SET user_pw=#{user_pw} WHERE user_id=#{user_id}")
	void modifyPassword(UserDTO user);

	// 회원 프로필 이미지 변경 메서드
	@Update("UPDATE " + USER + " SET profile_image=#{img_url} where user_id=#{user_id}")
	void updateProfileImg(UserDTO user);
}
