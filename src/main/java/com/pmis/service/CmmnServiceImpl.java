package com.pmis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmis.mapper.CmmnMapper;
import com.pmis.model.CmmnDTO;

@Service("CmmnService")
public class CmmnServiceImpl implements CmmnService {

	@Autowired
	private CmmnMapper cmmnMapper;

	public CmmnDTO findUser(String userName) {
		return cmmnMapper.findUser(userName);
	}
}
