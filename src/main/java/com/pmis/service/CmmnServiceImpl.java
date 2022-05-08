package com.pmis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmis.mapper.CmmnMapper;

@Service
public class CmmnServiceImpl implements CmmnService {

	@Autowired
	private CmmnMapper cmmnMapper;

}
