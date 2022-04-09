package com.pmis.mapper;

import org.apache.ibatis.annotations.Select;

import com.pmis.model.CmmnDTO;

public interface CmmnMapper {

	// 최근 등록된 물품 출력 메서드
	@Select("SELECT * FROM user WHERE user_name=#{userName}")
	CmmnDTO findUser(String userName);
}
