package com.mrt.mvc.model.service;

import org.springframework.stereotype.Service;

import com.mrt.mvc.model.dao.MagazineDao;

@Service
public class MagazineServiceImpl implements MagazineService {

	// 의존성 주입
	private final MagazineDao dao;
	public MagazineServiceImpl(MagazineDao dao) {
		this.dao = dao;
	}
	
}
